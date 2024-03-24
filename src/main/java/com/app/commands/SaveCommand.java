package com.app.commands;

import com.app.managers.CollectionManager;
import com.app.managers.FileManager;

import java.util.ArrayDeque;

public class SaveCommand implements Command {

    private FileManager fileManager;
    private CollectionManager collectionManager;

    public SaveCommand(FileManager fileManager, CollectionManager collectionManager) {
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "saves collection to file";
    }

    public void execute(String[] arguments) {
        fileManager.writeCollection(collectionManager.getProductCollection());
    }

}
