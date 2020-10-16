package fr.dinasty.epitalk.commands;

import fr.dinasty.epitalk.utils.ChannelUtils;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class KickCommand extends Command{
    Member commandSender;
    String command;
    String[] args;
    MessageReceivedEvent event;
    Guild guild;

    public KickCommand(Member commandSender, String command, String[] args, MessageReceivedEvent event) {
        this.commandSender = commandSender;
        this.command = command;
        this.args = args;
        this.event = event;
        guild = event.getGuild();
        execute();
    }

    void execute()
    {
        if(args.length < 2)
            return;

        if(!guild.getMembers().contains(guild.getMemberById(parserId(args[0]))))
        {
            event.getChannel().sendMessage("Membre non trouvé").queue();
            return;
        }

        Category category = ChannelUtils.getCategory("private", guild);
        if(category == null)
        {
            event.getChannel().sendMessage("Catégorie private non trouvée").queue();
            return;
        }

        GuildChannel channel = null;
        for (GuildChannel chan: guild.getChannels())
        {
            if(chan.getName().equals(args[1]) && category.getChannels().contains(chan))
            {
                channel = chan;
            }
        }

        if(channel == null)
        {
            event.getChannel().sendMessage("Channel non trouvé").queue();
            return;
        }

        if(!commandSender.getRoles().contains(guild.getRolesByName(args[1]+"Owner", true).get(0)))
        {
            event.getChannel().sendMessage("Vous ne pouvez retirer un membre dans un channel qui ne vous appartient pas").queue();
        }

        guild.removeRoleFromMember(guild.getMemberById(parserId(args[0])), guild.getRolesByName(args[1] + "Member", true).get(0)).complete();
    }

    private String parserId(String id)
    {
        return id.substring(3, id.length()-1);
    }
}
