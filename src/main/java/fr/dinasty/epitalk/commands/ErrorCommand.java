package fr.dinasty.epitalk.commands;

import fr.dinasty.epitalk.Main;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ErrorCommand extends Command{
    Member commandSender;
    String command;
    String[] args;
    MessageReceivedEvent event;

    public ErrorCommand(Member commandSender, String command, String[] args, MessageReceivedEvent event)
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
        StringBuilder stringBuilder = new StringBuilder();
        for (String command: Main.getCommandsHelper()) {
            stringBuilder.append(" "+command);
        }
        event.getChannel().sendMessage("Commande non trouvée, commandes disponible:"+ stringBuilder.toString()).queue();
    }
}