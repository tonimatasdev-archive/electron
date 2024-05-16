package dev.tonimatas.electron.api.blocks;

import dev.tonimatas.electron.api.Bot;

public class Block implements IBlock {
    protected String blockId;
    
    public Block(String blockId) {
        this.blockId = blockId;
    }
    
    @Override
    public boolean execute(Bot bot) {
        return false;
    }
}
