package com.neiizun.cordcoin.managers;

import com.neiizun.cordcoin.CordCoin;
import com.neiizun.cordcoin.objects.UserProfile;

import java.util.Timer;
import java.util.TimerTask;

public class MinerManager {
    private final CordCoin cordCoin;
    private final int minerHarvestInterval;
    private final double minerGain = 0.002;

    public MinerManager(CordCoin userProfile) {
        this.cordCoin = userProfile;
        this.minerHarvestInterval = userProfile.getConf().getMinerHarvestInterval();
        scheduleRepeatingTask();
    }

    public void scheduleRepeatingTask() {

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (UserProfile profile : cordCoin.getUsersManager().getUserProfiles().values()) {
                    if(profile.getMiners() > 0) {
                        profile.setCordCoins(profile.getCordCoins()+minerGain);
                        cordCoin.getUsersManager().saveProfile(profile.getUserID());
                    }
                }

            }
        }, minerHarvestInterval, minerHarvestInterval);
    }
}
