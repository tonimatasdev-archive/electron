package dev.tonimatas.discordmk.api.values.channel;

import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;

public interface VoiceChannelMK extends ChannelMK {
    @Override
    VoiceChannel getValue();
}
