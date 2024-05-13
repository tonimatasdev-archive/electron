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
    public static String token;
    public static String databaseUser; // TODO
    public static String databasePassword; // TODO
    public static List<String> allowedIps;
    public static boolean checkAllowedIps;
    
    public static void load() throws IOException {
        File propertiesFile = new File("controller.properties");

        Properties properties = new Properties();
        
        if (!propertiesFile.exists()) {
            //noinspection ResultOfMethodCallIgnored
            propertiesFile.createNewFile();
            properties.setProperty("port", "25555");
            properties.setProperty("token", "default");
            properties.setProperty("database_user", "username");
            properties.setProperty("database_password", "password");
            properties.setProperty("check_allowed_ips", "true");
            properties.setProperty("allowed_ips", "0.0.0.0,127.0.0.1,127.0.0.2");
            properties.store(new FileOutputStream(propertiesFile), null);
        }
        
        properties.load(new FileInputStream(propertiesFile));
        port = Integer.parseInt(properties.getProperty("port"));
        token = properties.getProperty("token");
        databaseUser = properties.getProperty("database_user");
        databasePassword = properties.getProperty("database_user");
        checkAllowedIps = Boolean.parseBoolean(properties.getProperty("check_allowed_ips"));
        allowedIps = Arrays.stream(properties.getProperty("allowed_ips").split(",")).toList();
    }
}
