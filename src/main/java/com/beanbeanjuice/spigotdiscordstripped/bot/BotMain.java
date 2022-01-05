package com.beanbeanjuice.spigotdiscordstripped.bot;

import com.beanbeanjuice.spigotdiscordstripped.helper.ChatHelper;
import com.beanbeanjuice.spigotdiscordstripped.helper.GeneralHelper;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.util.logging.Level;

public class BotMain {

    private static JDA jda;
    private static JDABuilder jdaBuilder;
    private static String BOT_TOKEN;
    private static String GUILD_ID;
    private static String TEXT_CHANNEL_ID;

    private static Guild guild;
    private static TextChannel textChannel;

    public BotMain(@NotNull String botToken, @NotNull String guildID, @NotNull String textChannelID) throws LoginException, InterruptedException {
        BOT_TOKEN = botToken;
        GUILD_ID = guildID;
        TEXT_CHANNEL_ID = textChannelID;

        jdaBuilder = JDABuilder.createDefault(BOT_TOKEN);
        jdaBuilder.setStatus(OnlineStatus.IDLE);
        jdaBuilder.setActivity(Activity.playing("Server is starting..."));

        jdaBuilder.addEventListeners(new ListenerAdapter() {
            @Override
            public void onMessageReceived(@NotNull MessageReceivedEvent event) {
                super.onMessageReceived(event);

                if (event.getGuild().getId().equals(GUILD_ID) && event.getTextChannel().getId().equals(TEXT_CHANNEL_ID)) {
                    if (!event.getAuthor().isBot()) {
                        ChatHelper.sendMinecraftChatMessage(event.getAuthor(), event.getMessage().getContentRaw());
                    }
                }
            }
        });

        jda = jdaBuilder.build().awaitReady();

        try {
            guild = jda.getGuildById(GUILD_ID);
        } catch (NullPointerException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Guild \"" + GUILD_ID + "\" Does Not Exist...");
        }

        try {
            textChannel = guild.getTextChannelById(TEXT_CHANNEL_ID);
        } catch (NullPointerException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Text Channel \"" + TEXT_CHANNEL_ID + "\" Does Not Exist...");
        }
    }

    public JDA getJDA() {
        return jda;
    }

    public static void sendChatMessage(@NotNull String minecraftUsername, @NotNull String minecraftFaceURL, @NotNull String messageContent) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.green);
        embedBuilder.setAuthor(minecraftUsername, null, minecraftFaceURL);
        embedBuilder.setDescription(messageContent);
        textChannel.sendMessageEmbeds(embedBuilder.build()).queue();
    }

    public static void sendJoinMessage(@NotNull String minecraftUsername) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.green);
        String message = GeneralHelper.getConfigString("discord_join_message");
        message = GeneralHelper.replaceUser(message, minecraftUsername);
        embedBuilder.setDescription(message);
        textChannel.sendMessageEmbeds(embedBuilder.build()).queue();
    }

    public static void sendQuitMessage(@NotNull String minecraftUsername) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.red);
        String message = GeneralHelper.getConfigString("discord_leave_message");
        message = GeneralHelper.replaceUser(message, minecraftUsername);
        embedBuilder.setDescription(message);
        textChannel.sendMessageEmbeds(embedBuilder.build()).queue();
    }

    public static void sendServerOnlineMessage() {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.green);
        embedBuilder.setDescription(GeneralHelper.getConfigString("server_online_message"));
        textChannel.sendMessageEmbeds(embedBuilder.build()).queue();
    }

}
