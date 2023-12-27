package com.mememan.celestibot.api.core;

import io.github.cdimascio.dotenv.Dotenv;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Optional;

public final class EnvManager {
    private static final EnvManager INSTANCE = new EnvManager();
    private Dotenv dotenv;

    private EnvManager() {}

    public static EnvManager getInstance() {
        return INSTANCE;
    }

    public void initialize(@Nullable Path envPath) {
        if (dotenv != null) throw new IllegalStateException("Dotenv already initialized!");

        this.dotenv = envPath != null ? Dotenv.configure().directory(envPath.toString()).load() : Dotenv.load();
    }

    public Optional<String> getBotToken() {
        return getString("BOT_TOKEN");
    }

    public Optional<String> getString(String envKey) {
        try {
            return Optional.ofNullable(dotenv.get(envKey));
        } catch (final NullPointerException exception) {
            exception.printStackTrace();
            return Optional.empty();
        }
    }
}
