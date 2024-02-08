package dev.tonimatas.discordmk.api;

import dev.tonimatas.discordmk.api.values.types.ValueMK;

public class BasicValue implements ValueMK {
    protected Object value;

    public BasicValue(Object value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return this.value;
    }
}
