package fr.dinasty.epitalk.commands;

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
        super(commandSender, command, args, event);
    }

    @Override
    void execute() {
        if(args.length <1)
            return;
        for (GuildChannel channel: event.getGuild().getChannels())
        {
            if(args[0].equalsIgnoreCase(channel.getName())){
                event.getChannel().sendMessage("Le nom n'est pas disponible").queue();
                return;
            }
        }

        event.getGuild().createTextChannel(args[0]).queue();

        GuildChannel newChannel;
        for (GuildChannel channel: event.getGuild().getChannels())
        {
            if(args[0].equalsIgnoreCase(channel.getName()))
            {
                newChannel = channel;
                break;
            }
        }

        

    }

}
