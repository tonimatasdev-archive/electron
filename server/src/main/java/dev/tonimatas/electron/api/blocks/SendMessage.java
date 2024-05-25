package dev.tonimatas.electron.api.blocks;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.tonimatas.electron.api.Bot;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

import java.util.Map;

public class SendMessage extends Block {
    protected String[] variables;
    
    public SendMessage(String id, String[] variables) {
        super(id);
        this.variables = variables;
    }

    @Override
    public Map<String, Object> execute(Bot bot, Map<String, Object> values) {
        String text = (String) values.get(variables[0]);
        MessageChannelUnion channel = (MessageChannelUnion) values.get(variables[1]);
        
        channel.sendMessage(text).queue();
        
        return Map.of();
    }
    
    public static SendMessage readJson(JsonObject blockJson) {
        String id = blockJson.get("id").getAsString();
        JsonArray jsonVariables = blockJson.get("variables").getAsJsonArray();
        String[] variables = new String[blockJson.get("variables").getAsJsonArray().size()];
        
        for (JsonElement jsonElement : jsonVariables) {
            String[] rawVariable = jsonElement.getAsString().split("-");
            variables[Integer.parseInt(rawVariable[0])] = rawVariable[1];
        }
        
        return new SendMessage(id, variables);
    }
}
