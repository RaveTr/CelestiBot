package com.mememan.celestibot.commands.base;


import com.mememan.celestibot.api.commands.CommandCategory;
import com.mememan.celestibot.api.commands.ICommandCategory;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.collections4.list.SetUniqueList;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.regex.Pattern;

//TODO: Better override hooking, cuz :skull:
public abstract class BaseCommand extends ListenerAdapter {
    protected static final Pattern VALID_PREFIX = Pattern.compile("[?]{1,4}|!{1,4}");

    public abstract String getName();
    public abstract String getDescription();

    public String getPrefix() {
        return "??";
    }

    public SetUniqueList<Permission> getRequiredPermissions() {
        return SetUniqueList.setUniqueList(Arrays.asList(Permission.ADMINISTRATOR));
    }

    public ICommandCategory getCategory() {
        return CommandCategory.DEFAULT;
    }

    public boolean existsAsSlash() {
        return true;
    }

    public boolean slashOnly() {
        return false;
    }

    public boolean prefixOnly() {
        return false;
    }

    public boolean accessibleViaDMs() {
        return false;
    }

    public boolean accessibleViaThreads() {
        return true;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {

    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        User author = event.getAuthor();
        Message receivedMessage = event.getMessage();
        String messageContent = receivedMessage.getContentDisplay();

        if (!assertCommandValidity(event, messageContent) || slashOnly() || author.isBot() || author.isSystem()) return;
        else {
            if (event.isFromGuild()) {
                Member member = event.getMember();
                EnumSet<Permission> memberPerms = member.getPermissions();

                memberPerms.forEach(perm -> {

                });
            }
        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!slashOnly() && !existsAsSlash() || prefixOnly()) return;

        if (event.isFromGuild()) {
            Member invoker = event.getMember();
            EnumSet<Permission> invokerPerms = invoker.getPermissions();

            invokerPerms.forEach(perm -> {

            });
        }
    }

    public static boolean isValidPrefix(String input) {
        return VALID_PREFIX.matcher(input).lookingAt();
    }

    public boolean assertCommandValidity(GenericMessageEvent event, String cmdMsg) { //TODO: Add more support for other Discord stuff like news channels. Also clean this up :skull:
        boolean isFromGuild = event.isFromGuild();
        boolean isFromThread = event.isFromThread();
        boolean isFromPrivateChannel = event.isFromType(ChannelType.PRIVATE);
        boolean assertChannelValidity = isFromGuild || (isFromPrivateChannel && accessibleViaDMs()) || (isFromThread && accessibleViaThreads());
        boolean assertPrefixValidity = !slashOnly() && prefixOnly() && isValidPrefix(cmdMsg);

        return assertChannelValidity && assertPrefixValidity;
    }
}
