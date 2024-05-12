package dev.tonimatas.electron.threads;

import dev.tonimatas.electron.ServerMK;
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
            while (!ServerMK.stopped) {
                Scanner scanner = new Scanner(System.in);
                String command = scanner.nextLine();
                CommandsMK.runCommand(command);
            }
        }).start();
    }

    public static void initReceiveThread(String host, int port) {
        new Thread(() -> {
            while (!ServerMK.stopped && ServerMK.socket != null) {
                try {
                    DataInputStream in = new DataInputStream(ServerMK.socket.getInputStream());
                    TasksMK.runTask(in.readUTF());
                } catch (IOException ignored) {
                    ServerMK.socket = null;
                    initConnectionThread(host, port);
                }
            }
        }).start();
    }

    public static void initConnectionThread(String host, int port) {
        while (!ServerMK.stopped && ServerMK.socket == null) {
            try {
                ServerMK.socket = new Socket(host, port);
            } catch (IOException e) {
                LoggerMK.error("Error on connect to the controller " + host + ":" + port + ". Next try in 30 seconds.");
                
                try {
                    //noinspection BusyWait
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception);
                }
            }
        }
        
        LoggerMK.info("Successfully connected to the controller " + host + ":" + port);
        ThreadsMK.initReceiveThread(host, port);
    }
}
