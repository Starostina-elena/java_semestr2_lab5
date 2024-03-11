package commands;

import managers.CollectionManager;

public class RemoveHeadCommand implements Command {

    private CollectionManager collectionManager;

    public RemoveHeadCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "shows first element of collection and deletes it";
    }

    public void execute(String[] arguments) {
        System.out.println(collectionManager.getProductCollection().pollFirst());
        System.out.println("Element was successfully deleted");
    }

}
