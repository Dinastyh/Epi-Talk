package fr.dinasty.epitalk.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ErrorCommand extends Command{
    Member commandSander;
    String command;
    String[] args;
    MessageReceivedEvent event;

    public ErrorCommand(Member commandSander, String command, String[] args, MessageReceivedEvent event) {
        super(commandSander, command, args, event);
    }

    @Override
    void execute()
    {
        event.getChannel().sendMessage("Commande non trouve").queue();
    }
}
