package dev.tonimatas.electron;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.tonimatas.electron.api.Bot;
import dev.tonimatas.electron.console.LoggerMK;
import dev.tonimatas.electron.reader.ReaderMK;
import dev.tonimatas.electron.threads.ThreadsMK;
import dev.tonimatas.electron.util.PropertiesMK;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ServerMK {
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static Map<String, Bot> bots = new HashMap<>();
    public static Socket socket;
    public static boolean stopped = false;
    public static boolean closed = true;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {

        try {
            PropertiesMK.load();
        } catch (IOException e) {
            LoggerMK.error("Error on load the server properties.");
            throw new RuntimeException(e);
        }

        File file = new File("bots");

        if (!file.exists()) file.mkdir();

        File[] files = file.listFiles();
        if (files == null) {
            LoggerMK.info("No bots to load");
        } else {
            Arrays.stream(files).forEach(botFile -> {
                Bot bot = new ReaderMK(botFile).build();
                bots.put(bot.getToken(), bot);
            });
        }

        bots.values().forEach(Bot::start);
        LoggerMK.info("Bots started successfully.");

        ThreadsMK.initConsoleThread();

        LoggerMK.info("Server started successfully.");

        ThreadsMK.initConnectionThread(PropertiesMK.controllerIp, PropertiesMK.port);
    }


    public static void stop() {
        stopped = true;
        bots.values().forEach(Bot::stop);

        try {
            socket.close();
        } catch (IOException e) {
            LoggerMK.error("Error on close the socket.");
        }

        LoggerMK.info("Server stopped correctly.");
    }
}
