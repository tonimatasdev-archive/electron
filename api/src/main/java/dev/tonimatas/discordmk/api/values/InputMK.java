package dev.tonimatas.discordmk.api.values;

import dev.tonimatas.discordmk.api.values.types.ValueMK;

public record InputMK(Object input) implements ValueMK {
    @Override
    public Object getValue() {
        return this.input;
    }
}
