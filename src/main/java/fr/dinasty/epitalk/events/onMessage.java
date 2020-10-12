package fr.dinasty.epitalk.events;

import fr.dinasty.epitalk.commands.*;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class onMessage implements EventListener {

    public void onEvent(@NotNull GenericEvent genericEvent) {

        if(!(genericEvent instanceof MessageReceivedEvent))
            return;

        MessageReceivedEvent event = (MessageReceivedEvent)genericEvent;
        String command = commandParserCommand(event.getMessage().getContentRaw());
        String args[] = commandParserArgs(event.getMessage().getContentRaw());

        if(command == null || command.charAt(0) != '!')
            return;

        if ("!create".equalsIgnoreCase(command) || "!new".equalsIgnoreCase(command) ) {
            new CreateCommand(event.getMember(), command, args, event);
        } else if ("!remove".equalsIgnoreCase(command) || "!delete".equalsIgnoreCase(command)) {
            new RemoveCommand(event.getMember(), command, args, event);
        } else if ("!addMember".equalsIgnoreCase(command) || "!add".equalsIgnoreCase(command)) {
            new AddCommand(event.getMember(), command, args, event);
        } else if ("!kickMember".equalsIgnoreCase(command) || "!kick".equalsIgnoreCase(command)) {
            new KickCommand(event.getMember(), command, args, event);
        } else {
            new ErrorCommand(event.getMember(), command, args, event);
        }
    }

    private String[] commandParserArgs(String message)
    {

        String from[];
        from = message.split(" ");
        String dest[] = new String[from.length-1];
        for(int i = 1; i < from.length; i++)
        {
            dest[i-1] = from[i];
        }
        return dest;
    }

    private String commandParserCommand(String message)
    {
        return message.split(" ")[0];
    }
}
