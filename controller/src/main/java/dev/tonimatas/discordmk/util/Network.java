package dev.tonimatas.discordmk.util;

import dev.tonimatas.discordmk.console.LoggerMK;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Network {
    public static boolean send(Socket socket, String message) {
        if (socket.isClosed()) {
            LoggerMK.error("The server " + socket.getInetAddress() + " is closed.");
            return false;
        }

        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(message);
            out.flush();
            return true;
        } catch (IOException e) {
            LoggerMK.error("Error on send the message to " + socket.getInetAddress());
            return false;
        }
    }
}