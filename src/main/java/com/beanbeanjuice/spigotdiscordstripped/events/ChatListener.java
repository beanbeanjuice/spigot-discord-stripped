package com.beanbeanjuice.spigotdiscordstripped.events;

import com.beanbeanjuice.spigotdiscordstripped.bot.BotMain;
import com.beanbeanjuice.spigotdiscordstripped.helper.SkinHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    void onPlayerChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        BotMain.sendChatMessage(player.getName(), SkinHelper.getPlayerHeadImage(player), event.getMessage());
    }

}
