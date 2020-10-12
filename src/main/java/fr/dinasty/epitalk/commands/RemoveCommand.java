package fr.dinasty.epitalk.commands;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class RemoveCommand extends Command{
    Member commandSender;
    String command;
    String[] args;
    MessageReceivedEvent event;

    public RemoveCommand(Member commandSender, String command, String[] args, MessageReceivedEvent event)
    {
        this.commandSender = commandSender;
        this.command = command;
        this.args = args;
        this.event = event;
        execute();
    }

    @Override
    void execute()
    {
        if(args.length < 1)
            return;
        Category category = getCategory("private");
        if(category == null)
        {
            event.getChannel().sendMessage("Channel not found").queue();
            return;
        }
        if(!commandSender.getRoles().contains(event.getGuild().getRolesByName(args[0]+"Owner", true).get(0)))
        {
            event.getChannel().sendMessage("Vous ne pouvez pas supprimer un channel qui ne vous appartient pas").queue();
            return;
        }
        for (GuildChannel chal: event.getGuild().getChannels())
        {
            if(chal.getName().equals(args[0]) && category.getChannels().contains(chal))
            {
                chal.delete().queue();
                event.getChannel().sendMessage("Channnel "+ args[0]+ " removed").queue();
                event.getGuild().getRolesByName(args[0]+"Owner", true).get(0).delete().queue();
                event.getGuild().getRolesByName(args[0]+"Member", true).get(0).delete().queue();
                return;
            }
        }
        event.getChannel().sendMessage("Channel not found").queue();
    }

    public Category getCategory(String name)
    {
        Category category = null;
        for(Category category1 : event.getGuild().getCategories())
        {
            if(category1.getName().equalsIgnoreCase(name))
            {
                category = category1;
            }
        }
        return category;
    }
}
