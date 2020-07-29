package com.neiizun.cordcoin.embeds;

import com.neiizun.cordcoin.enums.emojis.Emoji;
import com.neiizun.cordcoin.objects.UserProfile;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class ProfileEmbed extends EmbedBuilder {
    public ProfileEmbed(User user, UserProfile userProfile, JDA jda) {
        String userName = user.getName();

        this.setAuthor(Emoji.Trophy + " profile - " + userName, null, jda.getSelfUser().getAvatarUrl());
        this.addField("[" + Emoji.Moneybag + "]" + "  balance", userProfile.getCordCoins() + " CC", false);
        this.addField("[" + Emoji.FloppyDisk + "]" + "  miners", String.valueOf(userProfile.getMiners()), false);
        this.setFooter(Emoji.Sparkles + " CordCoin, the new discord coin bot !");
        this.setThumbnail(user.getAvatarUrl());
        this.setColor(Color.YELLOW);
    }
}
