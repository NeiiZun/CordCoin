package com.neiizun.cordcoin.commands;

import com.neiizun.cordcoin.CordCoin;
import com.neiizun.cordcoin.embeds.ProfileEmbed;
import com.neiizun.cordcoin.interfaces.CommandExecutor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class StartCommand implements CommandExecutor {
    private CordCoin cordCoin;

    public StartCommand(CordCoin cordCoin) {
        this.cordCoin = cordCoin;
    }

    @Override
    public void onCommand(JDA jda, User user, Guild guild, TextChannel textChannel, String message, String[] args) {
        String userID = user.getId();

        if(this.cordCoin.getUsersManager().getUserProfile(userID) != null) {
            textChannel.sendMessage(":octagonal_sign: you already have a profile").queue();
            return;
        }

        textChannel.sendMessage(new ProfileEmbed(this.cordCoin.getUsersManager().createProfile(userID), user.getName(), jda).build()).queue();
    }
}
