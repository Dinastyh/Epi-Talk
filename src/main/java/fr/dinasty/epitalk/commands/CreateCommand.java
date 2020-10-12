package fr.dinasty.epitalk.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.EnumSet;
import java.util.Random;

public class CreateCommand extends Command {

    Member commandSender;
    String command;
    String[] args;
    MessageReceivedEvent event;

    public CreateCommand(Member commandSender, String command, String[] args, MessageReceivedEvent event)
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
        //Cancel if args[0] == member id
        for(Member member : event.getGuild().getMembers())
        {
            if(args[0].equals("<@!"+member.getId()+">"))
            {
                event.getChannel().sendMessage("Vous ne pouvez pas creer un channel avec le nom d'un joueur").queue();
                return;
            }
        }
        //cancel if channel is alredy created
        for (GuildChannel chal: event.getGuild().getChannels())
        {
            if(chal.getName().equals(args[0]))
            {
                event.getChannel().sendMessage("Un channel à ce nom existe déjà").queue();
                return;
            }
        }
        Category category = getCategory();
        if(args.length> 1 && args[1].equalsIgnoreCase("vocal"))
            event.getGuild().createVoiceChannel(args[0]) .setParent(category).queue();
        else
            event.getGuild().createTextChannel(args[0]).setParent(category).queue();

        GuildChannel newChan = null;
        for (GuildChannel chan: event.getGuild().getChannels())
        {
            if(chan.getName().equals(args[0]))
            {
                newChan = chan;
                break;
            }
        }
        newRoles(args[0]);
        newChan.getManager().putPermissionOverride(Permission.VIEW_CHANNEL)
        event.getGuild().addRoleToMember(commandSender, event.getGuild().getRolesByName(args[0]+"Owner", true).get(0)).complete();
    }

    private Category getCategory()
    {
        Category category = null;
        for (Category categorySearch : event.getGuild().getCategories())
        {
            if(categorySearch.getName().equalsIgnoreCase("Private")){
                category = categorySearch;
            }
        }
        if(category == null)
        {
            event.getGuild().createCategory("Private").complete();
            category = getCategory();
        }
        return category;
    }

    private void newRoles(String name)
    {
        Random random = new Random();
        Color color = new Color(random.nextInt(255),random.nextInt(255), random.nextInt(255));
       event.getGuild().createRole().setName(name+"Member").setColor(color).complete();
       event.getGuild().createRole().setName(name+"Owner").setColor(color).complete();
    }
}
