package com.neiizun.cordcoin.managers;

import com.neiizun.cordcoin.CordCoin;
import com.neiizun.cordcoin.objects.UserProfile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UsersManager {
    private final CordCoin cordCoin;
    private final Map<String, UserProfile> userProfiles;

    public UsersManager(CordCoin cordCoin) {
        this.cordCoin = cordCoin;
        this.userProfiles = new HashMap<>();
        loadProfiles();
    }

    public UserProfile createProfile(String userID) {
        if(!this.userProfiles.containsKey(userID)) {
            UserProfile userProfile = new UserProfile(userID, 0.05, 0);
            userProfiles.put(userID, userProfile);
            saveProfile(userID);
            cordCoin.getActivityManager().updateActivity();
        }

        return userProfiles.get(userID);
    }


    public void saveProfile(String userID) {
        if(this.cordCoin.getFilesManager().getFile(userID) == null) {
            this.cordCoin.getFilesManager().createFile(new File("users/", userID+".json"), "users/"+userID);
        }

        UserProfile userProfile = this.userProfiles.get(userID);
        cordCoin.getFilesManager().saveText("users/"+userID, this.cordCoin.getJsonUtils().serialize(userProfile));
    }

    public UserProfile getUserProfile(String userID) {
        return this.userProfiles.get(userID);
    }
    
    private void loadProfiles() {
        for (File user : this.cordCoin.getFilesManager().getDirectoryFiles("users")) {
            String name = user.getName().replace(".yml", "");

            if(user.getName().endsWith(".json")) {
                this.cordCoin.getFilesManager().createFile(user, name);

                UserProfile userProfile = (UserProfile) this.cordCoin.getJsonUtils().deserialize(this.cordCoin.getFilesManager().getContent(name), UserProfile.class);

                this.userProfiles.put(userProfile.getUserID(), userProfile);
            }
        }
    }

    public int getUsersSize() {
        return this.userProfiles.values().size();
    }

    public double getTotalCordCoins() {
        double total = 0;

        for (UserProfile value : this.userProfiles.values()) {
            total += value.getCordCoins();
        }

        return total;
    }

    public Map<String, UserProfile> getUserProfiles() {
        return userProfiles;
    }
}
