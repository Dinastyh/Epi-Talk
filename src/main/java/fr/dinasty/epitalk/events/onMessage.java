package fr.dinasty.epitalk.events;

import fr.dinasty.epitalk.commands.AddCommand;
import fr.dinasty.epitalk.commands.CreateCommand;
import fr.dinasty.epitalk.commands.ErrorCommand;
import fr.dinasty.epitalk.commands.RemoveCommand;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Member;

public class onMessage implements EventListener {

    public void onEvent(@NotNull GenericEvent genericEvent) {

        if(!(genericEvent instanceof MessageReceivedEvent))
            return;

        MessageReceivedEvent event = (MessageReceivedEvent)genericEvent;
        String command = commandParserCommand(event.getMessage().getContentRaw());
        String args[] = commandParserArgs(event.getMessage().getContentRaw());

        if(command == null || command.charAt(0) != '!')
            return;

        switch (command)
        {
            case "!create":
                new CreateCommand(event.getMember(), command, args, event);
                break;
            case "!delete":
                new RemoveCommand(event.getMember(), command, args, event);
                break;
            case "!add":
                new AddCommand(event.getMember(), command, args, event);
                break;
            default:
                new ErrorCommand(event.getMember(), command, args, event);
                break;
        }
    }

    private String[] commandParserArgs(String message)
    {

        String from[];
        from = message.split(" ");
        String dest[] = new String[from.length-1];
        System.arraycopy(from, 1, dest,0, from.length);
        return dest;
    }

    private String commandParserCommand(String message)
    {
        return message.split(" ")[0];
    }
}
