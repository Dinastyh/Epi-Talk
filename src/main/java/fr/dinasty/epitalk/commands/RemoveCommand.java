package fr.dinasty.epitalk.commands;

import fr.dinasty.epitalk.utils.ChannelUtils;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class RemoveCommand extends Command{
    Member commandSender;
    String command;
    String[] args;
    MessageReceivedEvent event;
    Guild guild;

    public RemoveCommand(Member commandSender, String command, String[] args, MessageReceivedEvent event)
    {
        this.commandSender = commandSender;
        this.command = command;
        this.args = args;
        this.event = event;
        guild = event.getGuild();
        execute();
    }

    @Override
    void execute()
    {
        if(args.length < 1)
            return;
        Category category = ChannelUtils.getCategory("private", guild);
        if(category == null)
        {
            event.getChannel().sendMessage("Channel non trouvé").queue();
            return;
        }
        if(!commandSender.getRoles().contains(guild.getRolesByName(args[0]+"Owner", true).get(0)))
        {
            event.getChannel().sendMessage("Vous ne pouvez pas supprimer un channel qui ne vous appartient pas").queue();
            return;
        }
        for (GuildChannel chal: guild.getChannels())
        {
            if(chal.getName().equals(args[0]) && category.getChannels().contains(chal))
            {
                chal.delete().queue();
                event.getChannel().sendMessage("Channnel "+ args[0]+ " removed").queue();
                guild.getRolesByName(args[0]+"Owner", true).get(0).delete().queue();
                guild.getRolesByName(args[0]+"Member", true).get(0).delete().queue();
                return;
            }
        }
        event.getChannel().sendMessage("Channel not found").queue();
    }
}
