package dev.tonimatas.discordmk.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Messages {
    public static void send(Socket socket, String message) {
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String receive(Socket socket) {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            return in.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
