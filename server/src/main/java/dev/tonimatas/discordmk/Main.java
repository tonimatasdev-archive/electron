package dev.tonimatas.discordmk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.tonimatas.discordmk.api.Bot;
import dev.tonimatas.discordmk.console.CommandsMK;
import dev.tonimatas.discordmk.console.LoggerMK;
import dev.tonimatas.discordmk.reader.ReaderMK;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
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

        initConsoleThread();
        
        LoggerMK.info("Server started successfully.");

        // TODO: Possibility to change host and port
        String host = "localhost";
        int port = 2555;
        
        while (!stopped && socket == null) {
            try {
                socket = new Socket(host, port);
            } catch (IOException e) {
                LoggerMK.error("Error on connect to the server " + host + ":" + port + ". Next try in 30 seconds.");
            }

            try {
                //noinspection BusyWait
                Thread.sleep(30 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        
        LoggerMK.info("Successfully connected to the controller " + host + ":" + port);
        
        initReceiveThread();
    }
    
    public static void initConsoleThread() {
        new Thread(() -> {
            while (!stopped) {
                Scanner scanner = new Scanner(System.in);
                String command = scanner.nextLine();
                CommandsMK.runCommand(command);
            }
        }).start();
    }
    
    public static void initReceiveThread() {
        new Thread(() -> {
            while (!stopped) {
                try {
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    CommandsMK.runCommand(in.readUTF());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
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
