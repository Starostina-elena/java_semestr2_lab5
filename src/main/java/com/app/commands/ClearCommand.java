package com.app.commands;

import com.app.managers.CollectionManager;

import java.util.ArrayDeque;

public class ClearCommand implements Command {

    private CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "clears collection";
    }

    public void execute(String[] arguments) {
        collectionManager.setProductCollection(new ArrayDeque<>());
        System.out.println("Collection was successfully cleared");
    }

}
