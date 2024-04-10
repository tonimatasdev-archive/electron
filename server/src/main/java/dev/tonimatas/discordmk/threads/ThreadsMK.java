package dev.tonimatas.discordmk.threads;

import dev.tonimatas.discordmk.Main;
import dev.tonimatas.discordmk.console.CommandsMK;
import dev.tonimatas.discordmk.console.TasksMK;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ThreadsMK {
    public static void initConsoleThread() {
        new Thread(() -> {
            while (!Main.stopped) {
                Scanner scanner = new Scanner(System.in);
                String command = scanner.nextLine();
                CommandsMK.runCommand(command);
            }
        }).start();
    }

    public static void initReceiveThread() {
        new Thread(() -> {
            while (!Main.stopped) {
                try {
                    DataInputStream in = new DataInputStream(Main.socket.getInputStream());
                    TasksMK.runTask(in.readUTF());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
