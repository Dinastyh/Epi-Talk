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
    private static String[] commandsHelper;

    public static <Jda> void main(String[] args) throws LoginException {
        jda = new JDABuilder("PAS DE TOKEN ON GITHUB").build();
        eventsManager = new EventsManager();
        profilsManager = new ProfilsManager();
        commandsHelper= new String[]{"!create <NomduChannel>", "add <NomDuMembre>", "delete <NomDuChannel>", "remove <NomDuMembre>"};
    }

    public static JDA getJda() {
        return jda;
    }

    public static ProfilsManager getProfilsManager() {
        return profilsManager;
    }

    public static String[] getCommandsHelper() {
        return commandsHelper;
    }
}
