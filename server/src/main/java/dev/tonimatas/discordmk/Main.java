package dev.tonimatas.discordmk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.tonimatas.discordmk.api.Bot;
import dev.tonimatas.discordmk.console.CommandsMKImpl;
import dev.tonimatas.discordmk.console.ConsoleThread;
import dev.tonimatas.discordmk.console.LoggerMK;
import dev.tonimatas.discordmk.reader.ReaderMK;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static List<Bot> bots = new ArrayList<>();
    private static ConsoleThread consoleThread;
    
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

        consoleThread = new ConsoleThread(new CommandsMKImpl());
        consoleThread.start();
        LoggerMK.info("Server started successfully.");
    }
    
    public static void stop() {
        consoleThread.stopConsole();
        bots.forEach(Bot::stop);
        LoggerMK.info("Server stopped correctly.");
    }
}
