package dev.tonimatas.discordmk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.tonimatas.discordmk.api.Bot;
import dev.tonimatas.discordmk.reader.ReaderMK;

import java.io.File;
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
        
        // TODO: Add the server logic to connect with the Controller.

        System.out.println("Server started successfully.");
    }
}
