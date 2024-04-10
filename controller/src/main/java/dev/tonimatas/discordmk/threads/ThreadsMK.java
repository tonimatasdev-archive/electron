package dev.tonimatas.discordmk.threads;

import dev.tonimatas.discordmk.Main;
import dev.tonimatas.discordmk.console.CommandsMK;
import dev.tonimatas.discordmk.console.LoggerMK;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ThreadsMK {
    public static void initConsoleThread() {
        new Thread(() -> {
            while (!Main.stopped) {
                Scanner scanner = new Scanner(System.in);
                String command = scanner.nextLine();
                CommandsMK.runCommand(command);
            }
        }).start();
    }

    public static void initCheckThread() {
        new Thread(() -> {
            Main.sockets.removeIf(Socket::isClosed);

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void initAcceptThread() {
        new Thread(() -> {
            while (!Main.stopped) {
                try {
                    Socket socket = Main.serverSocket.accept();
                    initReceiveThread(socket);
                    Main.sockets.add(socket);

                    LoggerMK.info("Server connected. IP: " + socket.getInetAddress());
                } catch (IOException e) {
                    LoggerMK.error("Error on connect with a socket.");
                }
            }
        }).start();
    }

    public static void initReceiveThread(Socket socket) {
        new Thread(() -> {
            while (!Main.stopped) {
                try {
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    CommandsMK.runCommand(in.readUTF());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
