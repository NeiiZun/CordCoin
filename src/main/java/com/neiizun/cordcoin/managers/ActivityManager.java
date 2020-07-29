package com.neiizun.cordcoin.managers;

import com.neiizun.cordcoin.CordCoin;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class ActivityManager {
    private final CordCoin cordCoin;

    public ActivityManager(CordCoin cordCoin) {
        this.cordCoin = cordCoin;
        update();
    }

    public void updateActivity() {
        int usersCount = cordCoin.getUsersManager().getUsersSize();
        double cordCoinsCount = cordCoin.getUsersManager().getTotalCordCoins();

        String activityMessage = cordCoin.getBotProfile().getActivityMessage()
                .replace("{users}", String.valueOf(usersCount))
                .replace("{cordcoins}", String.valueOf(cordCoinsCount));

        cordCoin.getJda().getPresence().setActivity(Activity.listening(activityMessage));
    }

    private void update() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateActivity();
            }
        }, 5000, 30000);
    }
}

