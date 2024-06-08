package dev.tonimatas.electron.console;

import dev.tonimatas.electron.ServerMK;
import dev.tonimatas.electron.threads.ThreadsMK;
import dev.tonimatas.electron.utils.NetworkMK;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TasksMK {
    public static void runTask(String task) {
        String[] splitTask = task.split(":");
        String[] args = new String[splitTask.length - 1];

        System.arraycopy(splitTask, 1, args, 0, args.length);

        switch (splitTask[0]) {
            case "test" -> handleExample(args);
            case "bot" -> handleBot(args);
            case "controller-stop" -> handleControllerStop();
            default -> LoggerMK.error("Task \"" + splitTask[0] + "\" not found.");
        }
    }

    private static void handleExample(String[] args) {
        LoggerMK.info("Test received from " + args[0]);
        LoggerMK.info("Sending test to " + args[0]);
        NetworkMK.send(ServerMK.socket, "test:" + ServerMK.socket.getInetAddress().getHostAddress());
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void handleBot(String[] args) {
        String botId = args[0];
        String botJson = args[1];

        File file = new File("bots/" + botId + ".json");

        try {
            if (file.exists()) file.delete();
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(botJson);
            fileWriter.close();
        } catch (IOException e) {
            LoggerMK.info("Error on save bot: " + botId);
        }
    }

    public static void handleControllerStop() {
        ThreadsMK.initConnectionThread();
    }
}
