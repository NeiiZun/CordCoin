package com.neiizun.cordcoin.interfaces;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public interface CommandExecutor {
    void onCommand(User user, Guild guild, TextChannel textChannel, String message, String[] args);
}
