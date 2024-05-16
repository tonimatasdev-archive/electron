package dev.tonimatas.electron.threads;

import dev.tonimatas.electron.ControllerMK;
import dev.tonimatas.electron.console.CommandsMK;
import dev.tonimatas.electron.console.LoggerMK;
import dev.tonimatas.electron.console.TasksMK;
import dev.tonimatas.electron.util.PropertiesMK;
import dev.tonimatas.electron.utils.NetworkMK;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.Duration;
import java.util.Scanner;

public class ThreadsMK {
    public static void initConsoleThread() {
        new Thread(() -> {
            while (!ControllerMK.stopped) {
                Scanner scanner = new Scanner(System.in);
                String command = scanner.nextLine();
                CommandsMK.runCommand(command);
            }
        }).start();
    }

    public static void initAcceptThread() {
        new Thread(() -> {
            while (!ControllerMK.stopped) {
                try {
                    Socket socket = ControllerMK.serverSocket.accept();

                    if (PropertiesMK.allowedIps.contains(socket.getInetAddress().getHostAddress()) || !PropertiesMK.checkAllowedIps) {
                        NetworkMK.send(socket, "true");

                        DataInputStream in = new DataInputStream(socket.getInputStream());
                        String token = in.readUTF();

                        long time = System.nanoTime();
                        if (PropertiesMK.token.equals(token)) {
                            long waitTime = 5000000000L - (System.nanoTime() - time);
                            Thread.sleep(Duration.ofNanos(waitTime));

                            NetworkMK.send(socket, "true");
                            initReceiveThread(socket);
                            ControllerMK.sockets.add(socket);

                            LoggerMK.info("Server connected. IP: " + socket.getInetAddress().getHostAddress());
                        } else {
                            NetworkMK.send(socket, "false");
                            LoggerMK.warn("Server tried to connect with incorrect token. IP: " + socket.getInetAddress().getHostAddress());
                            socket.close();
                        }
                    } else {
                        NetworkMK.send(socket, "false");
                        LoggerMK.warn("A server (IP: " + socket.getInetAddress().getHostAddress() + ") tried to connect, but is not allowed in the config.");
                        socket.close();
                    }
                } catch (InterruptedException | IOException e) {
                    if (ControllerMK.stopped) return;
                    LoggerMK.error("Error on connect with a socket.");
                }
            }
        }).start();
    }

    public static void initReceiveThread(Socket socket) {
        new Thread(() -> {
            while (!ControllerMK.stopped && ControllerMK.sockets.contains(socket)) {
                try {
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    TasksMK.runTask(in.readUTF());
                } catch (IOException e) {
                    LoggerMK.info("Server disconnected. IP: " + socket.getInetAddress().getHostAddress());
                    ControllerMK.sockets.remove(socket);
                }
            }
        }).start();
    }
}
