package dev.tonimatas.electron.api.blocks;

import dev.tonimatas.electron.api.Bot;

public interface IBlock {
    boolean execute(Bot bot);
}
