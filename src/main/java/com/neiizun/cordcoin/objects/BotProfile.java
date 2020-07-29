package com.neiizun.cordcoin.objects;

import net.dv8tion.jda.api.OnlineStatus;

public class BotProfile {
    private final String token;
    private final OnlineStatus onlineStatus;

    public BotProfile(String token, OnlineStatus onlineStatus) {
        this.token = token;
        this.onlineStatus = onlineStatus;
    }

    public String getToken() {
        return token;
    }

    public OnlineStatus getOnlineStatus() {
        return onlineStatus;
    }
}
