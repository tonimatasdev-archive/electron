package dev.tonimatas.electron.api.values;

import dev.tonimatas.electron.api.values.types.ValueMK;

public record OutputMK(Object output) implements ValueMK {
    @Override
    public Object getValue() {
        return this.output;
    }
}
