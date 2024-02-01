package dev.tonimatas.discordmk.api.values;

import net.dv8tion.jda.api.entities.User;

public interface UserMK extends ValueMK {
    @Override
    User getValue();
}
