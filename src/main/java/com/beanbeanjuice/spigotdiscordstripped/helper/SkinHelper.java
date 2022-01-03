package com.beanbeanjuice.spigotdiscordstripped.helper;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SkinHelper {

    public static String getPlayerHeadImage(@NotNull Player player) {
        return "https://crafatar.com/avatars/" + player.getUniqueId();
    }

}
