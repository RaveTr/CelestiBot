package com.mememan.celestibot;

import com.mememan.celestibot.api.core.EnvManager;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class CelestiBot {
    public static final Logger LOGGER = LoggerFactory.getLogger(CelestiBot.class);
    public static final OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS).build();

    public static void main(String[] args) {
        EnvManager.getInstance().initialize(null);

        EnvManager.getInstance().getBotToken().ifPresentOrElse(botToken -> {
            JDA celestiBots = initializeAndBuildJDA(botToken);
        }, () -> {
            throw new NullPointerException("Bot token is not present in .env!");
        });

        LOGGER.info("Fully initialized CelestiBot!");
    }

    private static JDA initializeAndBuildJDA(String botToken) {
        JDABuilder eelBotBuilder = JDABuilder.createDefault(botToken);

        eelBotBuilder.setActivity(Activity.of(Activity.ActivityType.CUSTOM_STATUS, "Forging in the stars..."));
        eelBotBuilder.setAutoReconnect(true);

        eelBotBuilder.enableIntents(ObjectArrayList.of(GatewayIntent.values()));

        eelBotBuilder.enableCache(CacheFlag.EMOJI, CacheFlag.STICKER, CacheFlag.ONLINE_STATUS, CacheFlag.VOICE_STATE, CacheFlag.ROLE_TAGS, CacheFlag.MEMBER_OVERRIDES);
        eelBotBuilder.setChunkingFilter(ChunkingFilter.ALL);
        eelBotBuilder.setLargeThreshold(250);

        return eelBotBuilder.build();
    }
}
