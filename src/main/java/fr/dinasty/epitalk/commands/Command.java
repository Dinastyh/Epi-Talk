package fr.dinasty.epitalk.commands;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

abstract class Command {
    Member commandSander;
    String command;
    String[] args;
    MessageReceivedEvent event;

    abstract void execute();

}
