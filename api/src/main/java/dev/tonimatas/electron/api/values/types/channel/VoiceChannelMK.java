package dev.tonimatas.electron.api.values.types.channel;

import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;

public interface VoiceChannelMK extends ChannelMK {
    @Override
    VoiceChannel getValue();
}
