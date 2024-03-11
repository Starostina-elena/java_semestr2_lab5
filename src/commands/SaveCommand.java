package commands;

import managers.CollectionManager;
import managers.FileManager;

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
