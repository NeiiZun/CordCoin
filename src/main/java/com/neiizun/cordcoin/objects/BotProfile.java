package com.neiizun.cordcoin.objects;

import net.dv8tion.jda.api.OnlineStatus;

public class BotProfile {
    private final String token;
    private final OnlineStatus onlineStatus;
    private final String commandsPrefix;
    private final String version;

    public BotProfile(String token, OnlineStatus onlineStatus, String commandPrefix, String version) {
        this.token = token;
        this.onlineStatus = onlineStatus;
        this.commandsPrefix = commandPrefix;
        this.version = version;
    }

    public String getToken() {
        return token;
    }

    public OnlineStatus getOnlineStatus() {
        return onlineStatus;
    }

    public String getCommandsPrefix() {
        return commandsPrefix;
    }

    public String getVersion() {
        return version;
    }
}
