package dev.tonimatas.discordmk.api.values.channel;

import dev.tonimatas.discordmk.api.values.ValueMK;
import net.dv8tion.jda.api.entities.channel.Channel;

public interface ChannelMK extends ValueMK {
    @Override
    Channel getValue();
}
