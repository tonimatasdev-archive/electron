package dev.tonimatas.electron.api;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import java.util.HashMap;
import java.util.Map;

// TODO: Add 1000 lines
public class Bot {
    protected Map<String, Object> values = new HashMap<>();
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

    public JDA getBot() {
        return jda;
    }
    
    public Object getValue(int index, String id) throws Exception {
        Object object = this.values.get(index + "-id");
        
        if (object != null) {
            return object;
        } else {
            throw new RuntimeException("Error on getValue of index " + index + " on the function " + id);
        }
    }
}
