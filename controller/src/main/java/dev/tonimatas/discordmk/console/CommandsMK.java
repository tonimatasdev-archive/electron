package dev.tonimatas.discordmk.console;

import dev.tonimatas.discordmk.ControllerMK;
import dev.tonimatas.discordmk.utils.NetworkMK;

import java.net.Socket;

public class CommandsMK {
    public static void runCommand(String command) {
        switch (command) {
            case "stop" -> handleStop();
            case "test" -> handleTest();
            case "send" -> handleSend();
            // TODO: Server list command
            default -> LoggerMK.error("Command \"" + command + "\" not found.");
        }
    }
    
    private static void handleStop() {
        ControllerMK.stop();
    }
    
    private static void handleTest() {
        for (Socket socket : ControllerMK.sockets) {
            NetworkMK.send(socket, "test:" + ControllerMK.serverSocket.getInetAddress());
        }
    }
    
    private static void handleSend() {
        
    }
}
