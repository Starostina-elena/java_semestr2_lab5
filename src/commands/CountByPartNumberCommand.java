package commands;

import managers.CollectionManager;
import models.Product;

public class CountByPartNumberCommand implements Command {

    private CollectionManager collectionManager;

    public CountByPartNumberCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "shows number of element with specified part number. Pattern: count_by_part_number (String)partNumber";
    }

    public void execute(String[] arguments) {
        try {
            int cnt = 0;
            for (Product c : collectionManager.getProductCollection()) {
                if (c.getPartNumber().equals(arguments[1])) {
                    cnt++;
                }
            }
            System.out.println("There are " + cnt +  " products with this partNumber");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect number of arguments for count_by_part_number command. Please try again");
        }
    }

}
