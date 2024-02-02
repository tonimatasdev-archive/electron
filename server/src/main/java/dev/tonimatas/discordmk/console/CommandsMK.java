package dev.tonimatas.discordmk.console;

import dev.tonimatas.discordmk.Main;

public class CommandsMK {
    public static void runCommand(String command) {
        switch (command) {
            case "stop" -> handleStop();
            default -> LoggerMK.error("Command not found.");
        }
    }
    
    private static void handleStop() {
        Main.stop();
    }
}
