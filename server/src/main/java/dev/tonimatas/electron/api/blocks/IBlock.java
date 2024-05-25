package dev.tonimatas.electron.api.blocks;

import dev.tonimatas.electron.api.Bot;

import java.util.Map;

public interface IBlock {
    Map<String, Object> execute(Bot bot, Map<String, Object> values);
}
