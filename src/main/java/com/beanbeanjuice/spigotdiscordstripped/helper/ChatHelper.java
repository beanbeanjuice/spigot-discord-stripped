package com.beanbeanjuice.spigotdiscordstripped.helper;

import net.dv8tion.jda.api.entities.User;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class ChatHelper {

    public static void sendMinecraftChatMessage(@NotNull User user, @NotNull String messageContent) {
        Bukkit.broadcastMessage("(Discord) - " + user.getName() + ": " + messageContent);
    }

}
