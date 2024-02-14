package dev.tonimatas.discordmk.console;

import java.util.Scanner;

public class ConsoleThread extends Thread {
    private static boolean isStop;
    protected CommandsMK commands;
    
    public ConsoleThread(CommandsMK commands) {
        this.commands = commands;
    }

    public void stopConsole() {
        ConsoleThread.isStop = true;
    }

    @Override
    public void run() {
        while (!isStop) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            commands.runCommand(command);
        }
    }
}
