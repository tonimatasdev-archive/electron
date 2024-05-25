package dev.tonimatas.electron.reader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.tonimatas.electron.ServerMK;
import dev.tonimatas.electron.api.Bot;
import dev.tonimatas.electron.console.LoggerMK;
import dev.tonimatas.electron.workspaces.IWorkspace;
import dev.tonimatas.electron.workspaces.MessageReceived;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReaderMK {
    protected File jsonFile;

    public ReaderMK(File jsonFile) {
        this.jsonFile = jsonFile;
    }

    public Bot build() {
        JsonObject jsonObject;
        try {
            jsonObject = ServerMK.gson.fromJson(new FileReader(this.jsonFile), JsonObject.class);
        } catch (FileNotFoundException e) {
            System.out.println("Error on read JSON");
            return null;
        }

        String name = jsonObject.get("name").getAsString();
        String token = jsonObject.get("token").getAsString();

        JsonArray workspacesJson = jsonObject.get("workspaces").getAsJsonArray();
        
        List<IWorkspace> workspaces = new ArrayList<>();
        
        for (JsonElement jsonElement : workspacesJson) {
            JsonObject workspaceJson = jsonElement.getAsJsonObject();
            workspaces.add(readWorkspace(token, workspaceJson));
        }
        
        return new Bot(token, name, workspaces);
    }
    
    public static IWorkspace readWorkspace(String token, JsonObject workspaceJson) {
        String workspaceType = workspaceJson.get("type").getAsString();
        return switch (workspaceType) {
            case "message_received" -> MessageReceived.readJson(token, workspaceJson);
            default -> sendErrorReturningNull(workspaceType);
        };
    }
    
    public static IWorkspace sendErrorReturningNull(String workspace) {
        LoggerMK.error("The workspace \"" + workspace + "\" does not exist.");
        return null;
    }
}
