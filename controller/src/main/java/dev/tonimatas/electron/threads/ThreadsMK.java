package dev.tonimatas.electron.threads;

import dev.tonimatas.electron.ControllerMK;
import dev.tonimatas.electron.console.CommandsMK;
import dev.tonimatas.electron.console.LoggerMK;
import dev.tonimatas.electron.console.TasksMK;

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
