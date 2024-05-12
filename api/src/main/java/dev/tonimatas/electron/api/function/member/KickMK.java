package dev.tonimatas.electron.api.function.member;

import dev.tonimatas.electron.api.Bot;
import dev.tonimatas.electron.api.function.FunctionMK;
import net.dv8tion.jda.api.entities.Member;

public class KickMK extends FunctionMK {
    public KickMK(String id) {
        super(id);
    }

    @Override
    public boolean run(Bot bot) throws Exception {
        Member member = (Member) bot.getValue(1, this.id);

        if (member != null) {
            member.kick().queue();
            return true;
        }

        return false;
    }
}
