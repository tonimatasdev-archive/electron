package dev.tonimatas.discordmk;

import dev.tonimatas.discordmk.console.CommandsMKImpl;
import dev.tonimatas.discordmk.console.ConsoleThread;
import dev.tonimatas.discordmk.console.LoggerMK;

public class Main {
    private static ConsoleThread consoleThread;
    
    public static void main(String[] args) {
        consoleThread = new ConsoleThread(new CommandsMKImpl());
        consoleThread.start();
        LoggerMK.info("Server started successfully.");
    }

    public static void stop() {
        consoleThread.stopConsole();
        LoggerMK.info("Server stopped correctly.");
    }
}
