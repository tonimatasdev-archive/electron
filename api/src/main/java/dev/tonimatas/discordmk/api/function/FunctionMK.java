package dev.tonimatas.discordmk.api.function;

import dev.tonimatas.discordmk.api.Bot;
import dev.tonimatas.discordmk.api.values.types.ValueMK;

import java.util.Map;

public abstract class FunctionMK {
    protected String id;
    
    public FunctionMK(String id) {
        this.id = id;
    }
    
    public abstract void run(Bot bot, Map<String, ValueMK> values);
}