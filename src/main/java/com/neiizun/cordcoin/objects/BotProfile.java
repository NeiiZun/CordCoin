package com.neiizun.cordcoin.objects;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class BotProfile {
    private final String token;
    private final OnlineStatus onlineStatus;
    private final String commandsPrefix;
    private final String version;
    private final String activityMessage;

    public BotProfile(String token, OnlineStatus onlineStatus, String commandPrefix, String version, String activityMessage) {
        this.token = token;
        this.onlineStatus = onlineStatus;
        this.commandsPrefix = commandPrefix;
        this.version = version;
        this.activityMessage = activityMessage;
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



    public String getActivityMessage() {
        return activityMessage;
    }
}
