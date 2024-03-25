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
        try {
            boolean status = false;
            for (String c : commandsLIst.keySet()) {
                if (c.equals(arguments[1])) {
                    System.out.println(c + ": " + commandsLIst.get(c).description());
                    status = true;
                    break;
                }
            }
            if (!status) {
                System.out.println("no such command");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            for (String c : commandsLIst.keySet()) {
                System.out.println(c + ": " + commandsLIst.get(c).description());
            }
        }

    }

}
