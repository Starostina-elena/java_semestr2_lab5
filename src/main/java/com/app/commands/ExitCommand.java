package com.app.commands;

import com.app.managers.CollectionManager;
import com.app.managers.CommandManager;

import java.util.Scanner;

public class ExitCommand implements Command {

    CommandManager commandManager;

    public ExitCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public String description() {
        return "quits from program";
    }

    public void execute(String[] arguments) {
        System.out.println("Do you want to save your collection? Y/n");
        System.out.print("> ");
        Scanner in = new Scanner(System.in);
        String answer = in.nextLine().toUpperCase();
        while (!answer.equals("Y") & !answer.equals("N")) {
            System.out.println("Wrong input, try again. Do you want to save the collection? Y/n");
            System.out.print("> ");
            answer = in.nextLine().toUpperCase();
        }
        if (answer.equals("Y")) {
            commandManager.executeCommand("save");
        }
        System.out.println("goodbye");
        System.exit(0);
    }

}
