package dev.tonimatas.discordmk;

import dev.tonimatas.discordmk.console.CommandsMKImpl;
import dev.tonimatas.discordmk.console.ConsoleThread;
import dev.tonimatas.discordmk.console.LoggerMK;
import dev.tonimatas.discordmk.network.NetworkUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        // TODO: Properties
        Properties properties = new Properties();
        
        // TODO: Token
        
        // TODO: Connected server list

        String port = properties.getProperty("port");
        
        try {
            NetworkUtils.serverSocket = new ServerSocket(Integer.parseInt(port));
        } catch (IOException e) {
            LoggerMK.error("Error on create the server socket.");
        }
        
        if (NetworkUtils.serverSocket == null) stop();
        NetworkUtils.initServer(NetworkUtils.serverSocket);
        LoggerMK.info("Server uses port: " + port);
        
        Common.consoleThread = new ConsoleThread(new CommandsMKImpl());
        Common.consoleThread.start();
        
        LoggerMK.info("Controller started successfully.");
    }

    public static void stop() {
        Common.stopped = true;
        Common.consoleThread.stopConsole();
        
        try {
            NetworkUtils.serverSocket.close();
        } catch (IOException e) {
            LoggerMK.error("Error on close the server socket.");
        }
        
        LoggerMK.info("Controller stopped correctly.");
    }
}
