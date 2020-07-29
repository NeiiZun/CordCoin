package com.neiizun.cordcoin.listeners;

import com.neiizun.cordcoin.CordCoin;
import com.neiizun.cordcoin.interfaces.CommandExecutor;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BotEventListener implements EventListener {
    private final CordCoin cordCoin;

    public BotEventListener(CordCoin cordCoin) {
        this.cordCoin = cordCoin;
    }


    @Override
    public void onEvent(@Nonnull GenericEvent event) {
        if (event instanceof GuildMessageReceivedEvent) {
            onGuildMessageReceivedEvent((GuildMessageReceivedEvent) event);
        }
    }


    private void onGuildMessageReceivedEvent(GuildMessageReceivedEvent event) {
        String prefix = this.cordCoin.getBotProfile().getCommandsPrefix();
        String message = event.getMessage().getContentRaw();
        if(!message.startsWith(prefix)) return;

        String[] args = message.replace(prefix, "").split(" ");
        String name = args[0];

        for (Map.Entry<String[], CommandExecutor> entry : cordCoin.getCommandsManager().getCommands().entrySet()) {
            for (String s : entry.getKey()) {
                if (s.equalsIgnoreCase(name)) {
                    List<String> list = Arrays.asList(args);
                    list.remove(prefix + name);
                    args = (String[]) list.toArray();

                    entry.getValue().onCommand(this.cordCoin.getJda(), event.getAuthor(), event.getGuild(), event.getChannel(), message, args);
                }
            }
        }
    }


}
