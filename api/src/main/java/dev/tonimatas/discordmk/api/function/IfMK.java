package dev.tonimatas.discordmk.api.function;

import dev.tonimatas.discordmk.api.Bot;

public class IfMK extends FunctionMK {
    protected String type;
    
    public IfMK(String id, String type) {
        super(id);
        this.type = type;
    }

    @Override
    public boolean run(Bot bot) throws Exception {
        Object value1 = bot.getValue(1, id);
        Object value2 = bot.getValue(2, id);
        
        return switch (type) {
            case "==" -> value1.equals(value2);
            case "!=" -> (value1 != value2);
            default -> false;
        };
    }
}
