package dev.tonimatas.discordmk.console;

import dev.tonimatas.discordmk.ServerMK;
import dev.tonimatas.discordmk.utils.NetworkMK;

public class TasksMK {
    public static void runTask(String task) {
        String[] splitTask = task.split(":", 1);
        
        switch (splitTask[0]) {
            case "test" -> handleExample(splitTask[1]);
            default -> LoggerMK.error("Task \"" + splitTask[0] + "\" not found.");
        }
    }

    private static void handleExample(String args) {
        LoggerMK.info("Test received from " + args);
        LoggerMK.info("Sending test to " + args);
        NetworkMK.send(ServerMK.socket, "test:" + ServerMK.socket.getInetAddress());
    }
}
