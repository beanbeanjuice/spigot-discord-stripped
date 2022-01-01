package com.beanbeanjuice.spigotdiscordstripped;

import com.beanbeanjuice.spigotdiscordstripped.commands.DiscordStripped;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class SpigotDiscordStripped extends JavaPlugin {

    private PluginManager pluginManager;

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "Starting Spigot Discord Stripped...");
        saveDefaultConfig();
        pluginManager = Bukkit.getPluginManager();

        // Register Commands
        getCommand("discordstripped").setExecutor(new DiscordStripped());
        // Plugin startup logic
        getLogger().log(Level.FINE, "Spigot Discord Stripped has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @NotNull
    public Logger getLogger() {
        return Bukkit.getLogger();
    }
}
