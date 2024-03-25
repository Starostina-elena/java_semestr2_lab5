package com.app;

import com.app.managers.CollectionManager;
import com.app.managers.CommandManager;
import com.app.managers.FileManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


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
        CollectionManager collection;

        FileManager backup = new FileManager("urgentSaving.xml");
        if (backup.checkFileExists()) {
            System.out.println("backup was located. Do you want to restore your data? Y/n");
            System.out.print("> ");
            Scanner in = new Scanner(System.in);
            String answer = in.nextLine().toUpperCase();
            while (!answer.equals("Y") & !answer.equals("N")) {
                System.out.println("Wrong input, try again. Do you want to restore your data? Y/n");
                System.out.print("> ");
                answer = in.nextLine().toUpperCase();
            }
            if (answer.equals("Y")) {
                collection = backup.readCollection();
            } else {
                collection = fileManager.readCollection();
            }
            // удалить файл
            java.io.File file = new java.io.File("urgentSaving.xml");
            file.delete();
        } else {
            collection = fileManager.readCollection();
        }

        CommandManager commandManager = new CommandManager(collection, fileManager);
        commandManager.executeFromCommandLine();

    }
}