package dev.tonimatas.discordmk.api.values.types.channel;

import dev.tonimatas.discordmk.api.values.types.ValueMK;
import net.dv8tion.jda.api.entities.channel.Channel;

public interface ChannelMK extends ValueMK {
    @Override
    Channel getValue();
}
