package com.app.commands;

import com.app.managers.CollectionManager;

public class InfoCommand implements Command {

    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "shows short information on collection";
    }

    public void execute(String[] arguments) {
        System.out.println(collectionManager.shortInfo());
    }

}
