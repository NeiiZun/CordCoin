package com.neiizun.cordcoin.commands;

import com.neiizun.cordcoin.CordCoin;
import com.neiizun.cordcoin.embeds.BalanceEmbed;
import com.neiizun.cordcoin.interfaces.CommandExecutor;
import com.neiizun.cordcoin.objects.UserProfile;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class BalanceCommand implements CommandExecutor {
    private final CordCoin cordCoin;

    public BalanceCommand(CordCoin cordCoin) {
        this.cordCoin = cordCoin;
    }


    @Override
    public void onCommand(JDA jda, User user, Guild guild, TextChannel textChannel, String message, String[] args) {

        if (args.length < 1) {
            textChannel.sendMessage(new BalanceEmbed(user, this.cordCoin.getUsersManager().getUserProfile(user.getId()), jda).build()).queue();
            return;
        }

        String id = args[0];

        String[] toReplace = new String[]{"<", ">", "!", "@"};

        for (String c : toReplace) {
            id = id.replace(c, "");
        }

        user = null;
        UserProfile userProfile = this.cordCoin.getUsersManager().getUserProfile(id);

        try {
            user = jda.getUserById(id);
        } catch (Exception ignored) {

        }

        if (user == null || userProfile == null) {
            textChannel.sendMessage(cordCoin.getMessagesManager().getMessage("unknown_profile")).queue();
            return;
        }

        textChannel.sendMessage(new BalanceEmbed(user, userProfile, jda).build()).queue();
    }
}
