package dev.tonimatas.electron.api.function;

import dev.tonimatas.electron.api.Bot;

public abstract class FunctionMK {
    protected String id;
    
    public FunctionMK(String id) {
        this.id = id;
    }
    
    public abstract boolean run(Bot bot) throws Exception;
}
