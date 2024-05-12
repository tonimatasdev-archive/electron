package dev.tonimatas.electron.console;

import dev.tonimatas.electron.ServerMK;
import dev.tonimatas.electron.utils.NetworkMK;

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
