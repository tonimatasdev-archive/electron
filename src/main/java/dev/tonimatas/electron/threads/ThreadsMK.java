package dev.tonimatas.electron.threads;

import dev.tonimatas.electron.ServerMK;
import dev.tonimatas.electron.console.CommandsMK;
import dev.tonimatas.electron.console.LoggerMK;
import dev.tonimatas.electron.console.TasksMK;
import dev.tonimatas.electron.util.PropertiesMK;
import dev.tonimatas.electron.util.NetworkMK;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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

    public static void initReceiveThread() {
        initConnectionNow = false;
        new Thread(() -> {
            while (!ServerMK.stopped && ServerMK.socket != null) {
                try {
                    DataInputStream in = new DataInputStream(ServerMK.socket.getInputStream());
                    TasksMK.runTask(in.readUTF());
                } catch (IOException ignored) {
                    ServerMK.socket = null;
                    initConnectionThread();
                }
            }
        }).start();
    }

    public static boolean initConnectionNow = false;
    public static void initConnectionThread() {
        if (initConnectionNow) return;

        initConnectionNow = true;

        while (!ServerMK.stopped && ServerMK.socket == null) {
            try {
                Socket socket = new Socket(PropertiesMK.controllerIp, PropertiesMK.port);
                BufferedReader allowed = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                if (!Boolean.parseBoolean(allowed.readLine())) {
                    throw new Exception("Not allowed to connect to the controller " + PropertiesMK.controllerIp + ". Add this server ip to the allowed ips in the controller config.");
                }

                NetworkMK.send(socket, PropertiesMK.token);
                LoggerMK.info("Checking token. Wait 5 seconds.");
                BufferedReader token = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                if (!Boolean.parseBoolean(token.readLine())) {
                    throw new Exception("Incorrect token to connect to the controller " + PropertiesMK.controllerIp + ".");
                }


                ServerMK.socket = socket;
                LoggerMK.info("Successfully connected to the controller " + PropertiesMK.controllerIp + ":" + PropertiesMK.port);
                ThreadsMK.initReceiveThread();
            } catch (Exception e) {
                LoggerMK.error("Error on connect to the controller " + PropertiesMK.controllerIp + ":" + PropertiesMK.port + ". Next try in 30 seconds.");
                
                try {
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException exception) {
                    LoggerMK.error(exception.getMessage());
                    throw new RuntimeException(exception);
                }
            }
        }
    }
}
