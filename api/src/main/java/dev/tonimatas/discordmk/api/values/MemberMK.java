package dev.tonimatas.discordmk.api.values;

import net.dv8tion.jda.api.entities.Member;

public interface MemberMK extends ValueMK {
    @Override
    Member getValue();
}
