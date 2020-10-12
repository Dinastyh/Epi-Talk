package fr.dinasty.epitalk.commands;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class AddCommand extends Command{
    Member commandSender;
    String command;
    String[] args;
    MessageReceivedEvent event;

    public AddCommand(Member commandSender, String command, String[] args, MessageReceivedEvent event)
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
        if( args.length < 2)
            return;

        if(!event.getGuild().getMembers().contains(event.getGuild().getMemberById(parserId(args[0]))))
        {
            event.getChannel().sendMessage("Membre non trouvé").queue();
            return;
        }

        Category category = getCategory("private");
        if(category == null)
        {
            event.getChannel().sendMessage("Private non trouvé").queue();
            return;
        }

        GuildChannel channel = null;
        for (GuildChannel chan: event.getGuild().getChannels())
        {
            if(chan.getName().equals(args[1]) && category.getChannels().contains(chan))
            {
                channel = chan;
            }
        }

        if(channel == null)
        {
            event.getChannel().sendMessage("Channel not found").queue();
            return;
        }

        if(!commandSender.getRoles().contains(event.getGuild().getRolesByName(args[1]+"Owner", true).get(0)))
        {
            event.getChannel().sendMessage("Vous ne pouvez ajouter un membre dans un channel qui ne vous appartient pas").queue();
        }

        event.getGuild().addRoleToMember(event.getGuild().getMemberById(parserId(args[0])), event.getGuild().getRolesByName(args[1] + "Member", true).get(0)).complete();
    }

    private String parserId(String id)
    {
        return id.substring(3, id.length()-1);
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
