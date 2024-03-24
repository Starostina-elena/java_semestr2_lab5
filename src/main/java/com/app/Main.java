package com.app;

import com.app.managers.CollectionManager;
import com.app.managers.CommandManager;
import com.app.managers.FileManager;


/**
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        String fileName = System.getenv("collectionFileName");  //файл, где хранится начальное состояние коллекции
        if (fileName == null) {
            System.out.println("Please specify collectionFileName in environment variables");
            System.exit(0);
        }

        FileManager fileManager = new FileManager(fileName);

        CollectionManager collection = fileManager.readCollection();

        CommandManager commandManager = new CommandManager(collection, fileManager);
        commandManager.executeFromCommandLine();

    }
}