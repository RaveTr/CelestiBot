package com.mememan.celestibot.api.hooks;

import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import net.dv8tion.jda.api.JDA;

import java.util.Set;

public final class StopHookManager {
    private static final Set<Runnable> STOP_HOOKS = new ObjectLinkedOpenHashSet<>();

    public static void addHook(Runnable hook) {
        STOP_HOOKS.add(hook);
    }

    public static void surceaseBot(JDA botInstance) {
        botInstance.shutdown();
        STOP_HOOKS.forEach(Runnable::run);
    }
}
