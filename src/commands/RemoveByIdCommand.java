package commands;

import managers.CollectionManager;
import models.Coordinates;
import models.Organization;
import models.Product;
import models.UnitOfMeasure;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RemoveByIdCommand implements Command {

    private CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "removes element from collection by id";
    }

    public void execute(String[] arguments) {
        try {
            Product product = collectionManager.getById(Integer.parseInt(arguments[1]));
            System.out.println(product);
            collectionManager.removeFromCollection(product);
            System.out.println("Product was successfully deleted");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect number of arguments for update command. Please try again");
        } catch (NumberFormatException e) {
            System.out.println("id is not integer. Please try again");
        } catch (IllegalArgumentException e) {
            System.out.println(e + ". Please try again");
        }
    }

}
