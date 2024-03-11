package commands;

import managers.CollectionManager;
import models.Product;

public class RemoveLowerCommand implements Command {

    private CollectionManager collectionManager;

    public RemoveLowerCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "delete from collection all elements lower that selected. Pattern: remove_lower (long)id";
    }

    public void execute(String[] arguments) {
        try {
            Product product = collectionManager.getById(Integer.parseInt(arguments[1]));
            int counter = 0;
            for (Product c : collectionManager.getProductCollection()) {
                if (product.compareTo(c) > 0) {
                    counter++;
                    collectionManager.removeFromCollection(c);
                }
            }
            System.out.println(counter + " products were successfully deleted");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect number of arguments for remove_lower command. Please try again");
        } catch (NumberFormatException e) {
            System.out.println("id is not integer. Please try again");
        } catch (IllegalArgumentException e) {
            System.out.println(e + ". Please try again");
        }
    }

}
