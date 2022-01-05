package com.beanbeanjuice.spigotdiscordstripped.helper;

import net.dv8tion.jda.api.entities.User;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class ChatHelper {

    public static void sendMinecraftChatMessage(@NotNull User user, @NotNull String messageContent) {
        String message = GeneralHelper.getConfigString("discord_to_minecraft_chat");
        message = GeneralHelper.replaceMessage(message, messageContent);
        message = GeneralHelper.replaceUser(message, user.getName());
        message = GeneralHelper.replaceColours(message);
        Bukkit.broadcastMessage(message);
    }

}
