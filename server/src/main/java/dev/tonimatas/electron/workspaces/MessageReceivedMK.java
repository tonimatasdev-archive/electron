package dev.tonimatas.electron.workspaces;

import dev.tonimatas.electron.ServerMK;
import dev.tonimatas.electron.api.Bot;
import dev.tonimatas.electron.api.blocks.IBlock;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MessageReceivedMK extends ListenerAdapter {
    protected String botToken;
    protected List<IBlock> blocks;
    
    public MessageReceivedMK(String botToken, List<IBlock> blocks) {
        this.botToken = botToken;
        this.blocks = blocks;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Bot bot = ServerMK.bots.get(botToken);
        String text = event.getMessage().getContentRaw();
        MessageChannelUnion channel = event.getChannel();
        // TODO: Finish
        blocks.forEach(block -> block.execute(bot));
    }
}
