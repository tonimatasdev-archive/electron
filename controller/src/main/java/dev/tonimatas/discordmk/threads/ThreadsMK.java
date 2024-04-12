package dev.tonimatas.discordmk.threads;

import dev.tonimatas.discordmk.ControllerMK;
import dev.tonimatas.discordmk.console.CommandsMK;
import dev.tonimatas.discordmk.console.LoggerMK;
import dev.tonimatas.discordmk.console.TasksMK;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
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

    public static void initCheckThread() {
        new Thread(() -> {
            ControllerMK.sockets.removeIf(socket -> {
                LoggerMK.info("Server disconnected. IP: " + socket.getInetAddress().getHostAddress());
                return !socket.isClosed();
            });

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void initAcceptThread() {
        new Thread(() -> {
            while (!ControllerMK.stopped) {
                try {
                    Socket socket = ControllerMK.serverSocket.accept();
                    initReceiveThread(socket);
                    ControllerMK.sockets.add(socket);

                    LoggerMK.info("Server connected. IP: " + socket.getInetAddress().getHostAddress());
                } catch (IOException e) {
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
