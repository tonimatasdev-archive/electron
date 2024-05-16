package dev.tonimatas.electron.api.function.member;

import dev.tonimatas.electron.api.Bot;
import dev.tonimatas.electron.api.function.FunctionMK;
import net.dv8tion.jda.api.entities.Member;

import java.util.concurrent.TimeUnit;

public class BanMK extends FunctionMK {
    public BanMK(String id) {
        super(id);
    }

    @Override
    public boolean run(Bot bot) throws Exception {
        Member member = (Member) bot.getValue(1, this.id);
        int deletionTimeframe = (int) bot.getValue(3, this.id);
        String timeUnit = (String) bot.getValue(3, this.id);

        if (member != null) {
            member.ban(deletionTimeframe, TimeUnit.valueOf(timeUnit)).queue();
            return true;
        }

        return false;
    }
}
