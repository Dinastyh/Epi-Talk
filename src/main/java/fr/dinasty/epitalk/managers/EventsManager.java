package fr.dinasty.epitalk.managers;

import fr.dinasty.epitalk.Main;
import fr.dinasty.epitalk.events.onMessage;
import net.dv8tion.jda.api.JDA;

public class EventsManager {
    JDA jda;
    public EventsManager()
    {
        jda = Main.getJda();
        registerEvent();
    }

    private void registerEvent()
    {
        jda.addEventListener(new onMessage());
    }

}
