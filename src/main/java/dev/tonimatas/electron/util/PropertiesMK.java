package dev.tonimatas.electron.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesMK {
    public static int port;
    public static String token;
    public static String controllerIp;

    public static void load() throws IOException {
        File propertiesFile = new File("server.properties");

        Properties properties = new Properties();

        if (!propertiesFile.exists()) {
            //noinspection ResultOfMethodCallIgnored
            propertiesFile.createNewFile();
            properties.setProperty("port", "25555");
            properties.setProperty("token", "default");
            properties.setProperty("controller_ip", "0.0.0.0");
            properties.store(new FileOutputStream(propertiesFile), null);
        }

        properties.load(new FileInputStream(propertiesFile));
        port = Integer.parseInt(properties.getProperty("port"));
        token = properties.getProperty("token");
        controllerIp = properties.getProperty("controller_ip");
    }
}
