package dev.tonimatas.electron.api.function.text;

import dev.tonimatas.electron.api.Bot;
import dev.tonimatas.electron.api.function.FunctionMK;
import dev.tonimatas.electron.api.values.types.TextMK;
import dev.tonimatas.electron.api.values.types.channel.TextChannelMK;

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
