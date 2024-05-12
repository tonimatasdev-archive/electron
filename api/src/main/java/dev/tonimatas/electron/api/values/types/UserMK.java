package dev.tonimatas.electron.api.values.types;

import net.dv8tion.jda.api.entities.User;

public interface UserMK extends ValueMK {
    @Override
    User getValue();
}
