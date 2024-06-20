package dev.tonimatas.electron;

import dev.discordmk.quark.DiscordBot;
import dev.discordmk.quark.reader.ReaderMK;
import dev.tonimatas.electron.console.LoggerMK;
import dev.tonimatas.electron.threads.ThreadsMK;
import dev.tonimatas.electron.util.PropertiesMK;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerMK {
    public static Socket socket;
    public static boolean stopped = false;
    public static List<DiscordBot> bots = new ArrayList<>();

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        
        try {
            PropertiesMK.load();
        } catch (IOException e) {
            LoggerMK.error("Error on load the server properties.");
            throw new RuntimeException(e);
        }

        File file = new File("bots");

        if (!file.exists()) file.mkdir();

        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            LoggerMK.info("No bots to load");
        } else {
            Arrays.stream(files).forEach(botFile -> new Thread(() -> {
                try {
                    DiscordBot bot = new ReaderMK(botFile).build();
                    bots.add(bot);
                    LoggerMK.info("Loaded bot: " + botFile.getName());
                } catch (Exception e) {
                    LoggerMK.error("Failed to load bot from file: " + botFile.getName());
                }
            }).start());
        }

        LoggerMK.info("Bots started successfully.");

        ThreadsMK.initConsoleThread();

        LoggerMK.info("Server started successfully. Done (" + (float) ((System.currentTimeMillis() - time) / 1000) + ")!");

        ThreadsMK.initConnectionThread();
    }


    public static void stop() {
        stopped = true;
        bots.forEach(DiscordBot::stop);

        try {
            socket.close();
        } catch (IOException e) {
            LoggerMK.error("Error on close the socket.");
        }

        LoggerMK.info("Server stopped correctly.");
        System.exit(0);
    }
}
