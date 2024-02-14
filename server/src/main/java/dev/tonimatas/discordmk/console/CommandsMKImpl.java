package dev.tonimatas.discordmk.console;

import dev.tonimatas.discordmk.Main;

public class CommandsMKImpl implements CommandsMK {
    public void runCommand(String command) {
        switch (command) {
            case "stop" -> handleStop();
            default -> LoggerMK.error("Command not found.");
        }
    }
    
    private static void handleStop() {
        Main.stop();
    }
}
