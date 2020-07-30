package com.neiizun.cordcoin.commands;

import com.neiizun.cordcoin.CordCoin;
import com.neiizun.cordcoin.interfaces.CommandExecutor;
import com.neiizun.cordcoin.objects.UserProfile;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class BuyMinersCommand implements CommandExecutor {
    private final CordCoin cordCoin;

    public BuyMinersCommand(CordCoin cordCoin) {
        this.cordCoin = cordCoin;
    }

    @Override
    public void onCommand(JDA jda, User user, Guild guild, TextChannel textChannel, String message, String[] args) {
        if (args.length > 1) {
            textChannel.sendMessage("SYNTAXE ERR").queue();
            return;
        }

        UserProfile userProfile = this.cordCoin.getUsersManager().getUserProfile(user.getId());

        if (userProfile == null) {
            textChannel.sendMessage(this.cordCoin.getMessagesManager().getMessage("unknown_self_profile")).queue();
            return;
        }

        int amount = 0;

        try {
            amount = Integer.parseInt(args[0]);
        } catch (Exception ignored) {

        }

        if (amount == 0) {
            textChannel.sendMessage(this.cordCoin.getMessagesManager().getMessage("not_a_number")).queue();
            return;
        }

        double price = amount * this.cordCoin.getConf().getMinerPrice();

        if (userProfile.getCordCoins() < price) {
            textChannel.sendMessage(this.cordCoin.getMessagesManager().getMessage("no_many_money")).queue();
            return;
        }

        userProfile.setMiners(userProfile.getMiners() + amount);
        userProfile.setCordCoins(userProfile.getCordCoins() - price);

        this.cordCoin.getUsersManager().saveProfile(user.getId());

        textChannel.sendMessage(this.cordCoin.getMessagesManager().getMessage("successfully_bought_miners")
                .replace("{amount}", String.valueOf(amount))
                .replace("{price}", String.valueOf(price))).queue();
    }
}
