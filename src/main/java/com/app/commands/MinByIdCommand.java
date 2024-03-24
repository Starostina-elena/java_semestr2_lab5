package com.app.commands;

import com.app.managers.CollectionManager;

public class MinByIdCommand implements Command {

    private CollectionManager collectionManager;

    public MinByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "shows element with min id";
    }

    public void execute(String[] arguments) {
        System.out.println(collectionManager.getProductCollection().getFirst());
    }

}
