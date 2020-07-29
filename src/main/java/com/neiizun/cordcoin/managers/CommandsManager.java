package com.neiizun.cordcoin.managers;

import com.neiizun.cordcoin.CordCoin;
import com.neiizun.cordcoin.interfaces.CommandExecutor;

import java.util.HashMap;
import java.util.Map;

public class CommandsManager {
    private final Map<String[], CommandExecutor> commands;

    public CommandsManager() {
        this.commands = new HashMap<>();
    }

    public void registerCommand(String[] commands, CommandExecutor commandExecutor) {
        this.commands.put(commands, commandExecutor);
    }

    public CommandExecutor getCommand(String string) {
        for (Map.Entry<String[], CommandExecutor> entry : this.commands.entrySet()) {
            for (String s : entry.getKey()) {
                if (s.equals(string)) {
                    return entry.getValue();
                }
            }
        }

        return null;
    }

    public Map<String[], CommandExecutor> getCommands() {
        return commands;
    }
}
