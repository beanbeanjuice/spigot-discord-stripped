package com.beanbeanjuice.spigotdiscordstripped;

import com.beanbeanjuice.spigotdiscordstripped.bot.BotMain;
import com.beanbeanjuice.spigotdiscordstripped.commands.DiscordStripped;
import com.beanbeanjuice.spigotdiscordstripped.events.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class SpigotDiscordStripped extends JavaPlugin {

    private PluginManager pluginManager;

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "Starting Spigot Discord Stripped...");
        saveDefaultConfig();

        pluginManager = Bukkit.getPluginManager();

        String botToken = getConfig().getString("bot_token");
        String guildID = getConfig().getString("guild_id");
        String textChannelID = getConfig().getString("text_channel_id");
        BotMain bot;

        try {
            bot = new BotMain(botToken, guildID, textChannelID);
        } catch (LoginException e) {
            getLogger().log(Level.SEVERE, "The Bot Token Is Incorrect...");
        } catch (InterruptedException e) {
            getLogger().log(Level.SEVERE, "There was an error starting the bot...");
        }

        pluginManager.registerEvents(new ChatListener(), this);

        // Register Commands
        getCommand("discordstripped").setExecutor(new DiscordStripped());
        // Plugin startup logic
        getLogger().log(Level.FINE, "Spigot Discord Stripped has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
