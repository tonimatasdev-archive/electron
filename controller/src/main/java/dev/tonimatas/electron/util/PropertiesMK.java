package dev.tonimatas.electron.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PropertiesMK {
    public static int port;
    public static String token; // TODO
    public static String databaseUser; // TODO
    public static String databasePassword; // TODO
    public static List<String> allowedIps; // TODO
    
    public static void load() throws IOException {
        File propertiesFile = new File("config.properties");

        Properties properties = new Properties();
        
        if (!propertiesFile.exists()) {
            //noinspection ResultOfMethodCallIgnored
            propertiesFile.createNewFile();
            properties.setProperty("port", "2555");
            properties.setProperty("token", "default");
            properties.setProperty("database_user", "username");
            properties.setProperty("database_password", "password");
            properties.setProperty("allowed_ips", "0.0.0.0,127.0.0.1,127.0.0.2");
            properties.store(new FileOutputStream(propertiesFile), null);
        }
        
        properties.load(new FileInputStream(propertiesFile));
        port = Integer.parseInt(properties.getProperty("port"));
        token = properties.getProperty("token");
        databaseUser = properties.getProperty("database_user");
        databasePassword = properties.getProperty("database_user");
        allowedIps = Arrays.stream(properties.getProperty("allowed_ips").split(",")).toList();
    }
}
