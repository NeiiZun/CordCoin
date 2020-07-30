package com.neiizun.cordcoin.commands;

import com.neiizun.cordcoin.CordCoin;
import com.neiizun.cordcoin.interfaces.CommandExecutor;
import com.neiizun.cordcoin.objects.UserProfile;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class PayCommand implements CommandExecutor {
    private final CordCoin cordCoin;

    public PayCommand(CordCoin cordCoin) {
        this.cordCoin = cordCoin;
    }

    @Override
    public void onCommand(JDA jda, User user, Guild guild, TextChannel textChannel, String message, String[] args) {
        if(args.length < 2) {
            textChannel.sendMessage(cordCoin.getMessagesManager().getMessage("pay_syntax")).queue();
            return;
        }

        String id = args[0];

        String[] toReplace = new String[]{"<", ">", "!", "@"};

        for (String c : toReplace) {
            id = id.replace(c, "");
        }
        UserProfile userProfile = this.cordCoin.getUsersManager().getUserProfile(user.getId());
        UserProfile targetProfile = this.cordCoin.getUsersManager().getUserProfile(id);

        if (userProfile == null) {
            textChannel.sendMessage(this.cordCoin.getMessagesManager().getMessage("unknown_self_profile")).queue();
            return;
        }

        if (targetProfile == null) {
            textChannel.sendMessage(this.cordCoin.getMessagesManager().getMessage("unknown_profile")).queue();
            return;
        }


        double amount = 0;

        try {
            amount = Double.parseDouble(args[1]);
        } catch (Exception ignored) {
        }

        if(amount == 0) {
            textChannel.sendMessage(this.cordCoin.getMessagesManager().getMessage("not_a_number")).queue();
            return;
        }


        if(userProfile.getCordCoins() < amount) {
            textChannel.sendMessage(this.cordCoin.getMessagesManager().getMessage("no_many_money")).queue();
            return;
        }

        userProfile.setCordCoins(userProfile.getCordCoins()-amount);
        targetProfile.setCordCoins(targetProfile.getCordCoins()+amount);
        this.cordCoin.getUsersManager().saveProfile(userProfile.getUserID());
        this.cordCoin.getUsersManager().saveProfile(targetProfile.getUserID());
        textChannel.sendMessage(this.cordCoin.getMessagesManager().getMessage("successfully_paid")).queue();
    }
}
