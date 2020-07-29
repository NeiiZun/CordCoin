package com.neiizun.cordcoin.objects;

public class UserProfile {
    private final String userID;
    private final double cordCoins;
    private final int miners;

    public UserProfile(String userID, double cordCoins, int miners) {
        this.userID = userID;
        this.cordCoins = cordCoins;
        this.miners = miners;
    }

    public String getUserID() {
        return userID;
    }

    public double getCordCoins() {
        return cordCoins;
    }

    public int getMiners() {
        return miners;
    }
}
