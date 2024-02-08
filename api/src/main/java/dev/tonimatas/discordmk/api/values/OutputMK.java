package dev.tonimatas.discordmk.api.values;

import dev.tonimatas.discordmk.api.values.types.ValueMK;

public record OutputMK(Object output) implements ValueMK {
    @Override
    public Object getValue() {
        return this.output;
    }
}
