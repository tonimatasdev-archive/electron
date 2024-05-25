package dev.tonimatas.electron.api.blocks;

public abstract class Block implements IBlock {
    protected String id;
    
    public Block(String id) {
        this.id = id;
    }
}
