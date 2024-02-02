package dev.tonimatas.discordmk.console;

import java.util.Scanner;

public class ConsoleThread extends Thread {
    private static boolean isStop;

    public static void stopConsole() {
        ConsoleThread.isStop = true;
    }

    @Override
    public void run() {
        while (!isStop) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            CommandsMK.runCommand(command);
        }
    }
}
