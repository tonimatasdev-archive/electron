package dev.tonimatas.electron.api.blocks.statics;

public abstract class StaticBlock implements IStaticBlock {
    protected String blockId;
    
    public StaticBlock(String blockId) {
        this.blockId = blockId;
    }
}
