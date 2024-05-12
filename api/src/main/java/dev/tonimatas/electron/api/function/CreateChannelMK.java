package dev.tonimatas.electron.api.function;

import dev.tonimatas.electron.api.Bot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.ChannelType;

public class CreateChannelMK extends FunctionMK {
    public CreateChannelMK(String id) {
        super(id);
    }

    @Override
    public boolean run(Bot bot) throws Exception {
        String name = (String) bot.getValue(1, id);
        ChannelType type = (ChannelType) bot.getValue(2, id);
        Guild guild = (Guild) bot.getValue(3, id);
        
        switch (type) {
            case TEXT -> guild.createTextChannel(name).queue();
            case FORUM -> guild.createForumChannel(name).queue();
            case VOICE -> guild.createVoiceChannel(name).queue();
            case MEDIA -> guild.createMediaChannel(name).queue();
            case NEWS -> guild.createNewsChannel(name).queue();
            case CATEGORY -> guild.createCategory(name).queue();
            default -> {
                return false;
            }
        }
        return true;
    }
}
