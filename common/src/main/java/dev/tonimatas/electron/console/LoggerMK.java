package dev.tonimatas.electron.console;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerMK {
    public static void info(String message) {
        send("\u001B[32m", Type.INFO, message);
    }

    public static void warn(String message) {
        send("\u001B[33m", Type.WARN, message);
    }

    public static void error(String message) {
        send("\u001B[31m", Type.ERROR, message);
    }

    public static void send(Type type, String message) {
        switch (type) {
            case INFO -> info(message);
            case WARN -> warn(message);
            case ERROR -> error(message);
        }
    }
    
    private static void send(String color, Type type, String message) {
        System.out.println(color + "[" + getDate() + "] [" + type.name().toUpperCase() + "]: " + message);
        System.out.print("\u001B[0m");
    }

    private static String getDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
    
    public enum Type {
        ERROR,
        WARN,
        INFO
    }
}
