package dev.tonimatas.discordmk.api.values.types.channel;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public interface TextChannelMK extends ChannelMK {
    @Override
    TextChannel getValue();
}