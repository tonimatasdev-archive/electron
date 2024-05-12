package dev.tonimatas.electron.api.values;

import dev.tonimatas.electron.api.values.types.ValueMK;

public record InputMK(Object input) implements ValueMK {
    @Override
    public Object getValue() {
        return this.input;
    }
}
