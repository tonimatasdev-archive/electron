package dev.tonimatas.electron;

import dev.tonimatas.electron.console.LoggerMK;
import dev.tonimatas.electron.threads.ThreadsMK;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ControllerMK {
    public static ServerSocket serverSocket;
    public static List<Socket> sockets = new ArrayList<>();
    public static boolean stopped = false;
    
    public static void main(String[] args) {
        // TODO: Properties
        Properties properties = new Properties();
        
        int port = 2555; // properties.getProperty("port");
        
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            LoggerMK.error("Error on create the server socket.");
            throw new RuntimeException(e);
        }
        
        ThreadsMK.initAcceptThread();
        LoggerMK.info("Server uses port: " + port);
        
        ThreadsMK.initConsoleThread();
        
        LoggerMK.info("Controller started successfully.");
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
