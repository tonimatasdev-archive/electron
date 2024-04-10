package dev.tonimatas.discordmk.console;

import dev.tonimatas.discordmk.ServerMK;
import dev.tonimatas.discordmk.utils.NetworkMK;

public class CommandsMK {
    public static void runCommand(String command) {
        switch (command) {
            case "stop" -> handleStop();
            case "send" -> handleSend();
            default -> LoggerMK.error("Command \"" + command + "\" not found.");
        }
    }
    
    private static void handleStop() {
        ServerMK.stop();
    }
    
    private static void handleSend() {
        NetworkMK.send(ServerMK.socket, "Hello from server");
    }
}
