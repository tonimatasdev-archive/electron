package dev.tonimatas.electron.api.blocks.statics;

import java.util.Map;

public class StringBlock extends StaticBlock {
    protected String value;
    
    public StringBlock(String blockId, String value) {
        super(blockId);
        this.value = value;
    }

    @Override
    public Map<String, Object> execute() {
        return Map.of(blockId + "_0", value);
    }
}
