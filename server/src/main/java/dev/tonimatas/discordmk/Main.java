package dev.tonimatas.discordmk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.tonimatas.discordmk.api.Bot;
import dev.tonimatas.discordmk.console.CommandsMKImpl;
import dev.tonimatas.discordmk.console.ConsoleThread;
import dev.tonimatas.discordmk.console.LoggerMK;
import dev.tonimatas.discordmk.network.NetworkUtils;
import dev.tonimatas.discordmk.reader.ReaderMK;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static List<Bot> bots = new ArrayList<>();
    
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
        // TODO: Add the server logic to connect with the Controller.

        Common.consoleThread = new ConsoleThread(new CommandsMKImpl());
        Common.consoleThread.start();
        
        try {
            // TODO: Possibility to change host and port
            NetworkUtils.socket = new Socket("host", 2555);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        NetworkUtils.initClient();

        LoggerMK.info("Server started successfully.");
    }
    
    public static void stop() {
        Common.stopped = true;
        Common.consoleThread.stopConsole();
        bots.forEach(Bot::stop);

        try {
            NetworkUtils.socket.close();
        } catch (IOException e) {
            LoggerMK.error("Error on close the socket.");
        }
        
        LoggerMK.info("Server stopped correctly.");
    }
}
