package dev.tonimatas.electron.reader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.tonimatas.electron.api.blocks.IBlock;
import dev.tonimatas.electron.api.blocks.SendMessage;
import dev.tonimatas.electron.api.blocks.statics.IStaticBlock;
import dev.tonimatas.electron.api.blocks.statics.StringBlock;

import java.util.ArrayList;
import java.util.List;

public class BlockReaderMK {
    public static List<IBlock> readBlocks(JsonArray blocksJson) {
        List<IBlock> blocks = new ArrayList<>();

        for (JsonElement jsonElement : blocksJson) {
            IBlock block = readBlock(jsonElement.getAsJsonObject());
            if (block == null) continue;
            blocks.add(block);
        }
        
        return blocks;
    }

    public static List<IStaticBlock> readStaticBlocks(JsonArray blocksJson) {
        List<IStaticBlock> staticBlocks = new ArrayList<>();
        
        for (JsonElement jsonElement : blocksJson) {
            IStaticBlock block = readStaticBlock(jsonElement.getAsJsonObject());
            if (block == null) continue;
            staticBlocks.add(block);
        }
        
        return staticBlocks;
    }
    
    public static IBlock readBlock(JsonObject blockJson) {
        String blockType = blockJson.get("type").getAsString();

        return switch (blockType) {
            case "send_message" -> SendMessage.readJson(blockJson);
            default -> null;
        };
    }
    
    public static IStaticBlock readStaticBlock(JsonObject blockJson) {
        String blockType = blockJson.get("type").getAsString();
        String blockId = blockJson.get("id").getAsString();
        
        return switch (blockType) {
            case "string" -> new StringBlock(blockId, blockJson.get("value").getAsString());
            default -> null;
        };
    }
}
