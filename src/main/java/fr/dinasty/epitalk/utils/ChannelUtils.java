package fr.dinasty.epitalk.utils;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;

public class ChannelUtils {
    public static Category getCategory(String name, Guild guild)
    {
        Category category = null;
        for(Category category1 : guild.getCategories())
        {
            if(category1.getName().equalsIgnoreCase(name))
            {
                category = category1;
            }
        }
        return category;
    }
}
