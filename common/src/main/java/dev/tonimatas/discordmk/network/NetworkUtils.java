package dev.tonimatas.discordmk.network;

import dev.tonimatas.discordmk.Common;
import dev.tonimatas.discordmk.console.LoggerMK;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class NetworkUtils {
    public static List<Socket> sockets = new ArrayList<>();
    public static ServerSocket serverSocket;
    public static Socket socket;
    
    public static void initServer(ServerSocket serverSocket) {
        new Thread("serverSocket") {
            @Override
            public void run() {
                while (!Common.stopped) {
                    try {
                        Socket socket = serverSocket.accept();

                        sockets.add(socket);
                    } catch (IOException e) {
                        LoggerMK.error("Error on connect with a socket.");
                    }
                }
            }
        }.start();
        
        new Thread("serverReader") {
            @Override
            public void run() {
                while (!Common.stopped) {
                    sockets.forEach(Messages::receive);
                    sockets.forEach(Messages::send);
                    
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();
    }
    
    public static void initClient() {
        new Thread("clientSocket") {
            @Override
            public void run() {
                while (!Common.stopped) {
                    Messages.receive(socket);
                    Messages.send(socket);
                    
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
    }
}
