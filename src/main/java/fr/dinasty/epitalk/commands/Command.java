package fr.dinasty.epitalk.commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

abstract class Command {
    Member commandSander;
    String command;
    String[] args;
    MessageReceivedEvent event;

    public Command(Member commandSander, String command, String[] args, MessageReceivedEvent event)
    {
        this.commandSander = commandSander;
        this.command = command;
        this.args = args;
        this.event = event;
        execute();
    }

    abstract void execute();

}
