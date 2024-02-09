package dev.tonimatas.discordmk.api.function.text;

import dev.tonimatas.discordmk.api.Bot;
import dev.tonimatas.discordmk.api.function.FunctionMK;
import dev.tonimatas.discordmk.api.values.types.TextMK;
import dev.tonimatas.discordmk.api.values.types.channel.TextChannelMK;

public class SendMessageMK extends FunctionMK {
    public SendMessageMK(String id) {
        super(id);
    }

    @Override
    public boolean run(Bot bot) throws Exception {
        TextChannelMK textChannel = (TextChannelMK) bot.getValue(1, this.id);
        TextMK text = (TextMK) bot.getValue(2, this.id);
        // TODO: Add all possible message content.
        textChannel.getValue().sendMessage(text.getValue()).queue();
        return true;
    }
}
