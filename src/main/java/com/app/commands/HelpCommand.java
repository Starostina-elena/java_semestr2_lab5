package com.app.commands;

import com.app.managers.CommandManager;

import java.util.Map;

public class HelpCommand implements Command {

    CommandManager commandManager;

    public HelpCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public String description() {
        return "provides info on commands";
    }

    public void execute(String[] arguments) {
        Map<String, Command> commandsLIst = commandManager.getCommandsList();
        for (String c : commandsLIst.keySet()) {
            System.out.println(c + ": " + commandsLIst.get(c).description());
        }

    }

}
