package com.neiizun.cordcoin.embeds;

import com.neiizun.cordcoin.objects.UserProfile;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;

import java.awt.*;

public class ProfileEmbed extends EmbedBuilder {
    public ProfileEmbed(UserProfile userProfile, String userName, JDA jda) {
        this.setAuthor("profile - " + userName, jda.getSelfUser().getAvatarUrl());
        this.setDescription("profile of " + userName);
        this.addField("CordCoins", String.valueOf(userProfile.getCordCoins()), false);
        this.addField("Miners", String.valueOf(userProfile.getMiners()), false);
        this.setColor(Color.YELLOW);
    }
}
