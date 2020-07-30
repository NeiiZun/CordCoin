package com.neiizun.cordcoin;

import com.neiizun.cordcoin.commands.*;
import com.neiizun.cordcoin.listeners.EventsListener;
import com.neiizun.cordcoin.managers.*;
import com.neiizun.cordcoin.objects.BotProfile;
import com.neiizun.cordcoin.objects.Conf;
import com.neiizun.cordcoin.utils.JsonUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.File;

public class CordCoin {
    private final File confFile;
    private final Conf conf;
    private final MessagesManager messagesManager;
    private final FilesManager filesManager;
    private final JsonUtils jsonUtils;
    private final File botFile;
    private final BotProfile botProfile;
    private final CommandsManager commandsManager;
    private final UsersManager usersManager;
    private final ActivityManager activityManager;
    private JDA jda;

    public CordCoin() {
        this.messagesManager = new MessagesManager();
        this.filesManager = new FilesManager();
        this.jsonUtils = new JsonUtils();
        this.confFile = this.filesManager.createFile(new File("conf.json"), "conf");
        this.conf = (Conf) this.jsonUtils.deserialize(this.filesManager.getContent("conf"), Conf.class);
        this.botFile = this.filesManager.createFile(new File("bot.json"), "bot");
        this.botProfile = (BotProfile) this.jsonUtils.deserialize(this.filesManager.getContent("bot"), BotProfile.class);
        this.usersManager = new UsersManager(this);
        this.commandsManager = new CommandsManager();
        this.activityManager = new ActivityManager(this);

        try {
            this.jda = (new JDABuilder()).setToken(this.botProfile.getToken()).build();
            this.jda.getPresence().setStatus(this.botProfile.getOnlineStatus());
        } catch (LoginException e) {
            System.out.println("looks like token is not valid (check your bot.json file)");
        }

        registerCommands();
        this.jda.addEventListener(new EventsListener(this));
    }



    public void registerCommands() {
        this.commandsManager.registerCommand(new String[]{"start"}, new StartCommand(this));
        this.commandsManager.registerCommand(new String[]{"profile", "show"}, new ProfileCommand(this));
        this.commandsManager.registerCommand(new String[]{"balance", "bal"}, new BalanceCommand(this));
        this.commandsManager.registerCommand(new String[]{"pay"}, new PayCommand(this));
        this.commandsManager.registerCommand(new String[]{"buyminers"}, new BuyMinersCommand(this));
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

    public ActivityManager getActivityManager() {
        return activityManager;
    }

    public Conf getConf() {
        return conf;
    }
}
