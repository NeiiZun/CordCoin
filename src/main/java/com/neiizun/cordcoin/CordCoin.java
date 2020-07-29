package com.neiizun.cordcoin;

import com.neiizun.cordcoin.commands.BalanceCommand;
import com.neiizun.cordcoin.commands.ProfileCommand;
import com.neiizun.cordcoin.commands.StartCommand;
import com.neiizun.cordcoin.listeners.EventsListener;
import com.neiizun.cordcoin.managers.*;
import com.neiizun.cordcoin.objects.BotProfile;
import com.neiizun.cordcoin.utils.JsonUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.File;

public class CordCoin {
    private MessagesManager messagesManager;
    private FilesManager filesManager;
    private JsonUtils jsonUtils;
    private File botFile;
    private BotProfile botProfile;
    private CommandsManager commandsManager;
    private UsersManager usersManager;
    private ActivityManager activityManager;
    private JDA jda;

    public CordCoin() {
        this.onEnable();
    }

    public void onEnable() {
        this.messagesManager = new MessagesManager();
        this.filesManager = new FilesManager();
        this.jsonUtils = new JsonUtils();
        this.botFile = this.filesManager.createFile(new File("bot.json"), "bot");
        this.botProfile = (BotProfile) this.jsonUtils.deserialize(this.filesManager.getContent("bot"), BotProfile.class);
        this.usersManager = new UsersManager(this);
        this.commandsManager = new CommandsManager();
        this.activityManager = new ActivityManager(this);

        try {
            this.jda = (new JDABuilder()).setToken(this.botProfile.getToken()).build();
            this.jda.getPresence().setStatus(this.botProfile.getOnlineStatus());
        } catch (LoginException e) {
            e.printStackTrace();
        }

        registerCommands();
        this.jda.addEventListener(new EventsListener(this));
    }

    public void registerCommands() {
        this.commandsManager.registerCommand(new String[]{"start"}, new StartCommand(this));
        this.commandsManager.registerCommand(new String[]{"profile", "show"}, new ProfileCommand(this));
        this.commandsManager.registerCommand(new String[]{"balance", "bal"}, new BalanceCommand(this));
    }

    public MessagesManager getMessagesManager() {
        return messagesManager;
    }

    public FilesManager getFilesManager() {
        return this.filesManager;
    }

    public JsonUtils getJsonUtils() {
        return this.jsonUtils;
    }

    public File getBotFile() {
        return this.botFile;
    }

    public JDA getJda() {
        return this.jda;
    }

    public BotProfile getBotProfile() {
        return this.botProfile;
    }

    public CommandsManager getCommandsManager() {
        return commandsManager;
    }

    public UsersManager getUsersManager() {
        return usersManager;
    }
}
