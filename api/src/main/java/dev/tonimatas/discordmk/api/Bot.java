package dev.tonimatas.discordmk.api;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

// TODO: Add 1000 lines
public class Bot {
    protected String token;
    protected String name;
    protected JDA jda;
    protected Thread thread;
    
    public Bot(String token, String name) {
        this.token = token;
        this.name = name;
        this.thread = new Thread(() -> {
            JDABuilder jdaBuilder = JDABuilder.createDefault(token);
            Bot.this.jda = jdaBuilder.build();
        });
    }
    
    public void start() {
        this.thread.start();
    }
    
    public void stop() {
        this.jda.shutdown();
        this.thread.interrupt();
    }

    public String getName() {
        return name;
    }
}
