package dev.tonimatas.electron.workspaces;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.tonimatas.electron.ServerMK;
import dev.tonimatas.electron.api.Bot;
import dev.tonimatas.electron.api.blocks.IBlock;
import dev.tonimatas.electron.api.blocks.statics.IStaticBlock;
import dev.tonimatas.electron.reader.BlockReaderMK;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageReceived implements IWorkspace {
    protected String botToken;
    protected List<IBlock> blocks;
    protected List<IStaticBlock> staticBlocks;
    
    public MessageReceived(String botToken, List<IBlock> blocks, List<IStaticBlock> staticBlocks) {
        this.botToken = botToken;
        this.blocks = blocks;
        this.staticBlocks = staticBlocks;
    }
    
    @Override
    public ListenerAdapter build() {
        return new ListenerAdapter() {
            @Override
            public void onMessageReceived(@NotNull MessageReceivedEvent event) {
                if (event.getAuthor().isBot()) return;
                Bot bot = ServerMK.bots.get(botToken);
                Map<String, Object> values = new HashMap<>();

                values.put("primary_0", event.getMessage().getContentRaw());
                values.put("primary_1", event.getChannel());
                
                staticBlocks.forEach(staticBlock -> values.putAll(staticBlock.execute()));
                // TODO: Actions need to work correctly
                blocks.forEach(block -> values.putAll(block.execute(bot, values)));
            }
        };
    }
    
    public static IWorkspace readJson(String botToken, JsonObject workspaceJson) {
        JsonArray blocksJson = workspaceJson.get("blocks").getAsJsonArray();
        
        List<IBlock> blocks = BlockReaderMK.readBlocks(blocksJson);
        List<IStaticBlock> staticBlocks = BlockReaderMK.readStaticBlocks(blocksJson);
        return new MessageReceived(botToken, blocks, staticBlocks);
    }
}
