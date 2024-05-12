package dev.tonimatas.electron;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.tonimatas.electron.api.Bot;
import dev.tonimatas.electron.console.LoggerMK;
import dev.tonimatas.electron.reader.ReaderMK;
import dev.tonimatas.electron.threads.ThreadsMK;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerMK {
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static List<Bot> bots = new ArrayList<>();
    public static Socket socket;
    public static boolean stopped = false;
    
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {
        File file = new File("bots");
        
        if (!file.exists()) file.mkdir();
        
        File[] files = file.listFiles();
        if (files == null) {
            System.out.println("No bot to load.");
        } else {
            Arrays.stream(files).forEach(botFile -> bots.add(new ReaderMK(botFile).build()));
        }
        
        bots.forEach(Bot::start);
        LoggerMK.info("Bots started successfully.");

        ThreadsMK.initConsoleThread();
        
        LoggerMK.info("Server started successfully.");

        // TODO: Possibility to change host and port
        String host = "localhost";
        int port = 2555;

        ThreadsMK.initConnectionThread(host, port);
    }
    
    
    
    public static void stop() {
        stopped = true;
        bots.forEach(Bot::stop);

        try {
            socket.close();
        } catch (IOException e) {
            LoggerMK.error("Error on close the socket.");
        }
        
        LoggerMK.info("Server stopped correctly.");
    }
}
