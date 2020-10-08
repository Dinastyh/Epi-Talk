package fr.dinasty.epitalk.commands;

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

    }

}
