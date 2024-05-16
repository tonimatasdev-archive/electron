package dev.tonimatas.electron.console;

public class TasksMK {
    public static void runTask(String task) {
        String[] splitTask = task.split(":");

        switch (splitTask[0]) {
            case "test" -> handleExample(splitTask[1]);
            default -> LoggerMK.error("Task \"" + splitTask[0] + "\" not found.");
        }
    }

    private static void handleExample(String args) {
        LoggerMK.info("Test works fine with " + args);
    }
}
