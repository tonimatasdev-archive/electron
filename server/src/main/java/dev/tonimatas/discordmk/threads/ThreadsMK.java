package dev.tonimatas.discordmk.threads;

import dev.tonimatas.discordmk.Main;
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
            while (!Main.stopped) {
                Scanner scanner = new Scanner(System.in);
                String command = scanner.nextLine();
                CommandsMK.runCommand(command);
            }
        }).start();
    }

    public static void initReceiveThread() {
        new Thread(() -> {
            while (!Main.stopped && Main.socket != null) {
                try {
                    DataInputStream in = new DataInputStream(Main.socket.getInputStream());
                    TasksMK.runTask(in.readUTF());
                } catch (IOException ignored) {
                    Main.socket = null;
                }
            }
        }).start();
    }
    
    public static void initCheckConnection(String host, int port) {
        new Thread(() -> {
            while (!Main.stopped) {
                if (Main.socket == null) {
                    initConnectionThread(host, port);
                }
                
                try {
                    //noinspection BusyWait
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public static void initConnectionThread(String host, int port) {
        while (!Main.stopped && Main.socket == null) {
            try {
                Main.socket = new Socket(host, port);
            } catch (IOException e) {
                LoggerMK.error("Error on connect to the server " + host + ":" + port + ". Next try in 30 seconds.");
                
                try {
                    //noinspection BusyWait
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception);
                }
            }
        }
        
        LoggerMK.info("Successfully connected to the controller " + host + ":" + port);
        ThreadsMK.initReceiveThread();
    }
}
