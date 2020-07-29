package com.neiizun.cordcoin;

import com.neiizun.cordcoin.listeners.BotEventListener;
import com.neiizun.cordcoin.managers.CommandsManager;
import com.neiizun.cordcoin.managers.FilesManager;
import com.neiizun.cordcoin.objects.BotProfile;
import com.neiizun.cordcoin.utils.JsonUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.File;

public class CordCoin {
    private FilesManager filesManager;
    private JsonUtils jsonUtils;
    private File botFile;
    private BotProfile botProfile;
    private CommandsManager commandsManager;
    private JDA jda;

    public CordCoin() {
        this.onEnable();
    }

    public void onEnable() {
        this.filesManager = new FilesManager();
        this.jsonUtils = new JsonUtils();
        this.botFile = this.filesManager.createFile("bot.json", "bot");
        this.botProfile = (BotProfile) this.jsonUtils.deserialize(this.filesManager.getContent("bot"), BotProfile.class);
        this.commandsManager = new CommandsManager(this);

        try {
            this.jda = (new JDABuilder()).setToken(this.botProfile.getToken()).build();
            this.jda.getPresence().setStatus(this.botProfile.getOnlineStatus());
        } catch (LoginException e) {
            e.printStackTrace();
        }

        registerCommands();
        this.jda.addEventListener(new BotEventListener(this));
    }

    public void registerCommands() {
        //register commands
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
}
