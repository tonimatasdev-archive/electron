package dev.tonimatas.discordmk.console;

import dev.tonimatas.discordmk.Main;

public class CommandsMK {
    public static void runCommand(String command) {
        switch (command) {
            case "stop" -> handleStop();
            case "send" -> handleSend();
            default -> LoggerMK.error("Command \"" + command + "\" not found.");
        }
    }
    
    private static void handleStop() {
        Main.stop();
    }
    
    private static void handleSend() {
        
    }
}
