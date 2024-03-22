package dev.tonimatas.discordmk.console;

import dev.tonimatas.discordmk.Main;
import dev.tonimatas.discordmk.network.Messages;

import java.net.Socket;

public class CommandsMK {
    public static void runCommand(String command) {
        switch (command) {
            case "stop" -> handleStop();
            case "test" -> handleTest();
            default -> LoggerMK.error("Command \"" + command + "\" not found.");
        }
    }
    
    private static void handleStop() {
        Main.stop();
    }
    
    private static void handleTest() {
        for (Socket socket : Main.sockets) {
            Messages.send(socket, "Hello");
        }
    }
}
