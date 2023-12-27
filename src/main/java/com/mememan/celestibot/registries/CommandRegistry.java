package com.mememan.celestibot.registries;

import com.google.common.collect.ImmutableList;
import com.mememan.celestibot.commands.base.BaseCommand;

import java.util.LinkedList;

public final class CommandRegistry {
    private static final LinkedList<BaseCommand> REGISTERED_COMMANDS = new LinkedList<>();

    private CommandRegistry() {}

    public static ImmutableList<BaseCommand> getRegisteredCommands() {
        return ImmutableList.copyOf(REGISTERED_COMMANDS);
    }
}
