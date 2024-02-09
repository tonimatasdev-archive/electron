package dev.tonimatas.discordmk.api.function;

import dev.tonimatas.discordmk.api.Bot;

public abstract class FunctionMK {
    protected String id;
    
    public FunctionMK(String id) {
        this.id = id;
    }
    
    public abstract boolean run(Bot bot) throws Exception;
}
