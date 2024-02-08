package dev.tonimatas.discordmk.api.function;

import dev.tonimatas.discordmk.api.Bot;
import dev.tonimatas.discordmk.api.values.types.TextMK;
import dev.tonimatas.discordmk.api.values.types.ValueMK;
import dev.tonimatas.discordmk.api.values.types.channel.TextChannelMK;

import java.util.Map;

public class SendMessageMK extends FunctionMK {
    public SendMessageMK(String id) {
        super(id);
    }

    @Override
    public void run(Bot bot, Map<String, ValueMK> values) {
        TextChannelMK textChannel = (TextChannelMK) values.get("1-" + this.id);
        TextMK text = (TextMK) values.get("1-" + this.id);
        // TODO: Add all possible message content.
        textChannel.getValue().sendMessage(text.getValue()).queue();
    }
}
