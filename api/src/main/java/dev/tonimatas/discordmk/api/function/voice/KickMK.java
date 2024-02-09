package dev.tonimatas.discordmk.api.function.voice;

import dev.tonimatas.discordmk.api.Bot;
import dev.tonimatas.discordmk.api.function.FunctionMK;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;

public class KickMK extends FunctionMK {
    public KickMK(String id) {
        super(id);
    }

    @Override
    public boolean run(Bot bot) throws Exception {
        Member member = (Member) bot.getValue(1, this.id);
        GuildVoiceState state = member.getVoiceState();
        
        if (state != null && state.inAudioChannel()) {
            member.getVoiceState().getGuild().kickVoiceMember(member).queue();
            return true;
        }
        
        return false;
    }
}
