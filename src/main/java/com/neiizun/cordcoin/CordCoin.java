package com.neiizun.cordcoin;

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
    private JDA jda;

    public CordCoin() {
        this.onEnable();
    }

    public void onEnable() {
        this.filesManager = new FilesManager();
        this.jsonUtils = new JsonUtils();
        this.botFile = this.filesManager.createFile("bot.json", "bot");
        this.botProfile = (BotProfile) this.jsonUtils.deserialize(this.filesManager.getContent("bot"), BotProfile.class);

        try {
            this.jda = (new JDABuilder()).setToken(this.botProfile.getToken()).build();
            this.jda.getPresence().setStatus(this.botProfile.getOnlineStatus());
        } catch (LoginException e) {
            e.printStackTrace();
        }

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
}
