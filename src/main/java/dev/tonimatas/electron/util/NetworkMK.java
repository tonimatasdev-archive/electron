package dev.tonimatas.electron.util;

import dev.tonimatas.electron.console.LoggerMK;

import java.io.*;
import java.net.Socket;

public class NetworkMK {
    public static boolean send(Socket socket, String message) {
        if (socket.isClosed()) {
            LoggerMK.error("The server " + socket.getInetAddress() + " is closed.");
            return false;
        }

        try {
            OutputStream output = socket.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            writer.write(message);
            writer.newLine();
            writer.flush();
            return true;
        } catch (IOException e) {
            LoggerMK.error("Error on send the message to " + socket.getInetAddress());
            return false;
        }
    }
}
