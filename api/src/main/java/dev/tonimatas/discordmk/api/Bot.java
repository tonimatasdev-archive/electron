package dev.tonimatas.discordmk.api;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

// TODO: Add 1000 lines
public class Bot {
    protected String token;
    protected JDA jda;
    
    public Bot(String token) {
        this.token = token;

        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        this.jda = jdaBuilder.build();
    }
}
