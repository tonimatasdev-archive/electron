package dev.tonimatas.electron;

import dev.tonimatas.electron.console.LoggerMK;
import dev.tonimatas.electron.threads.ThreadsMK;
import dev.tonimatas.electron.util.PropertiesMK;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ControllerMK {
    public static ServerSocket serverSocket;
    public static List<Socket> sockets = new ArrayList<>();
    public static boolean stopped = false;
    
    public static void main(String[] args) {
        try {
            PropertiesMK.load();
        } catch (IOException e) {
            LoggerMK.error("Error on load the controller properties.");
            throw new RuntimeException(e);
        }

        try {
            serverSocket = new ServerSocket(PropertiesMK.port);
        } catch (IOException e) {
            LoggerMK.error("Error on create the controller socket.");
            throw new RuntimeException(e);
        }

        ThreadsMK.initAcceptThread();
        LoggerMK.info("Server uses port: " + PropertiesMK.port);

        ThreadsMK.initConsoleThread();

        SpringApplication.run(ControllerMK.class, args);
        LoggerMK.info("Controller started successfully.");
    }

    public static void stop() {
        stopped = true;

        try {
            serverSocket.close();
        } catch (IOException e) {
            LoggerMK.error("Error on close the controller socket.");
        }

        LoggerMK.info("Controller stopped correctly.");
    }
}
