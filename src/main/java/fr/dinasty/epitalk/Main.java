package fr.dinasty.epitalk;

import fr.dinasty.epitalk.managers.EventsManager;
import fr.dinasty.epitalk.managers.ProfilsManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {
    private static JDA jda;
    private static EventsManager eventsManager;
    private static ProfilsManager profilsManager;

    public static <Jda> void main(String[] args) throws LoginException {
        jda = new JDABuilder("RgxCsl1EgV1a1SbqA0zjUcGZ0I55OG0L").build();
        eventsManager = new EventsManager();
        profilsManager = new ProfilsManager();
    }

    public static JDA getJda() {
        return jda;
    }

    public static ProfilsManager getProfilsManager() {
        return profilsManager;
    }
}
