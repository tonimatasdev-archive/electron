package dev.tonimatas.discordmk.api;

import java.io.File;

// TODO: Add 1000 lines
public class Bot {
    protected String filePath;
    
    public Bot(File filePath) {
        this.filePath = filePath.getPath();
    }
}
