package com.beanbeanjuice.spigotdiscordstripped.helper;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class GeneralHelper {

    private static JavaPlugin plugin;

    public static String replaceColours(@NotNull String string) {
        return string.replace("&", "§");
    }

    public static void setPlugin(JavaPlugin plugin) {
        GeneralHelper.plugin = plugin;
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static String getConfigString(@NotNull String string) {
        return plugin.getConfig().getString(string);
    }

    public static String replaceUser(@NotNull String string, @NotNull String username) {
        return string.replace("{username}", username);
    }

    public static String replaceMessage(@NotNull String string, @NotNull String message) {
        return string.replace("{message}", message);
    }

}
