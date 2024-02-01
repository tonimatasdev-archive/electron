package dev.tonimatas.discordmk.reader;

import dev.tonimatas.discordmk.api.Bot;

import java.io.File;

public class ReaderMK {
    protected File jsonFile;
    
    public ReaderMK(File jsonFile) {
        this.jsonFile = jsonFile;
    }
    
    public Bot build() {
        // Create all read logic
        return new Bot(jsonFile);
    }
}
