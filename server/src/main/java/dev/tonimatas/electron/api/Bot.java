package dev.tonimatas.electron.api;

import dev.tonimatas.electron.workspaces.IWorkspace;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.List;

public class Bot {
    protected String token;
    protected String name;
    protected JDA jda;

    public Bot(String token, String name, List<IWorkspace> workspaces) {
        this.token = token;
        this.name = name;
        JDABuilder builder = JDABuilder.createDefault(token);

        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.setBulkDeleteSplittingEnabled(false);

        if (!workspaces.isEmpty()) {
            workspaces.forEach(workspace -> builder.addEventListeners(workspace.build()));
        }
        
        this.jda = builder.build();
    }

    public void stop() {
        this.jda.shutdown();
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public JDA getBot() {
        return jda;
    }
}
