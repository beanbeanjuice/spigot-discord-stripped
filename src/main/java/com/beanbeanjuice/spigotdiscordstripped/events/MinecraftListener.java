package com.beanbeanjuice.spigotdiscordstripped.events;

import com.beanbeanjuice.spigotdiscordstripped.bot.BotMain;
import com.beanbeanjuice.spigotdiscordstripped.helper.SkinHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MinecraftListener implements Listener {

    @EventHandler
    void onPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        BotMain.sendChatMessage(player.getName(), SkinHelper.getPlayerHeadImage(player), event.getMessage());
    }

    @EventHandler
    void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        BotMain.sendJoinMessage(player.getName());
    }

    @EventHandler
    void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        BotMain.sendQuitMessage(player.getName());
    }

}
