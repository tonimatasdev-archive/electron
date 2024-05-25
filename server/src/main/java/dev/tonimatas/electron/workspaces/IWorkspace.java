package dev.tonimatas.electron.workspaces;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

public interface IWorkspace {
    ListenerAdapter build();
}
