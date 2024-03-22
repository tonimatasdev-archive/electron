package dev.tonimatas.discordmk;

import dev.tonimatas.discordmk.console.CommandsMK;
import dev.tonimatas.discordmk.console.LoggerMK;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static ServerSocket serverSocket;
    public static List<Socket> sockets = new ArrayList<>();
    public static boolean stopped = false;
    
    public static void main(String[] args) {
        // TODO: Properties
        Properties properties = new Properties();
        
        // TODO: Token
        
        // TODO: Connected server list

        String port = "2555"; // properties.getProperty("port");
        
        try {
            serverSocket = new ServerSocket(Integer.parseInt(port));
        } catch (IOException e) {
            LoggerMK.error("Error on create the server socket.");
        }
        
        if (serverSocket == null) stop();
        initAcceptThread();
        initCheckThread();
        LoggerMK.info("Server uses port: " + port);
        
        initConsoleThread();
        
        LoggerMK.info("Controller started successfully.");
    }

    public static void initConsoleThread() {
        new Thread(() -> {
            while (!stopped) {
                Scanner scanner = new Scanner(System.in);
                String command = scanner.nextLine();
                CommandsMK.runCommand(command);
            }
        }).start();
    }
    
    public static void initCheckThread() {
        new Thread(() -> {
            sockets.removeIf(Socket::isClosed);
            
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void initAcceptThread() {
        new Thread(() -> {
            while (!stopped) {
                try {
                    Socket socket = serverSocket.accept();

                    sockets.add(socket);

                    LoggerMK.info("Server connected. IP: " + socket.getInetAddress());
                } catch (IOException e) {
                    LoggerMK.error("Error on connect with a socket.");
                }
            }
        }).start();
    }

    public static void stop() {
        stopped = true;
        
        try {
            serverSocket.close();
        } catch (IOException e) {
            LoggerMK.error("Error on close the server socket.");
        }
        
        LoggerMK.info("Controller stopped correctly.");
    }
}
