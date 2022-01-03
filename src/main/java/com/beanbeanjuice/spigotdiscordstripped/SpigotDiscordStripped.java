package com.beanbeanjuice.spigotdiscordstripped;

import com.beanbeanjuice.spigotdiscordstripped.bot.BotMain;
import com.beanbeanjuice.spigotdiscordstripped.commands.DiscordStripped;
import com.beanbeanjuice.spigotdiscordstripped.events.MinecraftListener;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.util.logging.Level;

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
        BotMain bot = null;

        try {
            bot = new BotMain(botToken, guildID, textChannelID);
        } catch (LoginException e) {
            getLogger().log(Level.SEVERE, "The Bot Token Is Incorrect...");
        } catch (InterruptedException e) {
            getLogger().log(Level.SEVERE, "There was an error starting the bot...");
        }

        pluginManager.registerEvents(new MinecraftListener(), this);

        // Register Commands
        getCommand("discordstripped").setExecutor(new DiscordStripped());
        // Plugin startup logic
        getLogger().log(Level.FINE, "Spigot Discord Stripped has been enabled!");

        if (bot != null) {
            bot.getJDA().getPresence().setStatus(OnlineStatus.ONLINE);
            bot.getJDA().getPresence().setActivity(Activity.playing("Server is online!"));
            BotMain.sendServerOnlineMessage();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
