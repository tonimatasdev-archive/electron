package dev.tonimatas.discordmk.reader;

import com.google.gson.JsonObject;
import dev.tonimatas.discordmk.Main;
import dev.tonimatas.discordmk.api.Bot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReaderMK {
    protected File jsonFile;
    
    public ReaderMK(File jsonFile) {
        this.jsonFile = jsonFile;
    }
    
    public Bot build() {
        JsonObject jsonObject;
        try {
            jsonObject = Main.gson.fromJson(new FileReader(this.jsonFile), JsonObject.class);
        } catch (FileNotFoundException e) {
            System.out.println("Error on read JSON");
            return null;
        }
        
        String token = jsonObject.get("token").getAsString();
        

        // Create all read logic
        return new Bot(token);
    }
}
