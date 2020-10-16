package fr.dinasty.epitalk.commands;

import fr.dinasty.epitalk.utils.ChannelUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.EnumSet;
import java.util.Random;

public class CreateCommand extends Command {

    Member commandSender;
    String command;
    String[] args;
    MessageReceivedEvent event;
    Guild guild;

    public CreateCommand(Member commandSender, String command, String[] args, MessageReceivedEvent event)
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
        String name = args[0];
        for(Member member : guild.getMembers())
        {
            if(name.equals("<@!"+member.getId()+">"))
            {
                event.getChannel().sendMessage("Vous ne pouvez pas creer un channel avec le nom d'un joueur").queue();
                return;
            }
        }
        //cancel if channel is alredy created
        for (GuildChannel chal: guild.getChannels())
        {
            if(chal.getName().equals(name))
            {
                event.getChannel().sendMessage("Un channel à ce nom existe déjà").queue();
                return;
            }
        }
        newRoles(name);
        Role roleOwner = guild.getRolesByName(name+"Owner", true).get(0);
        Role roleMember = guild.getRolesByName(name+"Member", true).get(0);

        Category category = ChannelUtils.getCategory("private", guild);
        if(args.length> 1 && args[1].equalsIgnoreCase("vocal"))
            guild.createVoiceChannel(name)
                    .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .addPermissionOverride(roleOwner, EnumSet.of(Permission.VIEW_CHANNEL), null)
                    .setParent(guild.getCategoriesByName("Private", true).get(0))
                    .complete();
        else
            guild.createTextChannel(name)
                .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                .addPermissionOverride(roleOwner, EnumSet.of(Permission.VIEW_CHANNEL), null)
                .addPermissionOverride(roleMember, EnumSet.of(Permission.VIEW_CHANNEL), null)
                .setParent(guild.getCategoriesByName("Private", true).get(0))
                .complete();

        guild.addRoleToMember(commandSender, guild.getRolesByName(name+"Owner", true).get(0)).complete();
    }

    private void newRoles(String name)
    {
        Random random = new Random();
        Color color = new Color(random.nextInt(255),random.nextInt(255), random.nextInt(255));
       guild.createRole().setName(name+"Member").setColor(color).complete();
       guild.createRole().setName(name+"Owner").setColor(color).complete();
    }
}
