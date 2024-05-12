package dev.tonimatas.electron.api.values.types;

import net.dv8tion.jda.api.entities.Member;

public interface MemberMK extends ValueMK {
    @Override
    Member getValue();
}
