import managers.CollectionManager;
import managers.CommandManager;
import managers.FileManager;
import models.Coordinates;
import models.Organization;
import models.Product;
import models.UnitOfMeasure;

import java.util.ArrayDeque;
import java.util.Collections;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
/**Main class*/
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        String fileName = System.getenv("collectionFileName");  //файл, где хранится начальное состояние коллекции
        if (fileName == null)  {
            System.out.println("Please specify collectionFileName in environment variables");
            System.exit(0);
        }

//        Organization org1 = new Organization("name1", "fullname1", 20);
//        Organization org2 = new Organization("name2", "fullname2", 21);
//
//        Coordinates coords1 = new Coordinates(12L, 12.0);
//        Coordinates coords2 = new Coordinates(11L, 14.0);
//
//        Product prod1 = new Product("prod1", coords1, 120, "12f",
//                100, UnitOfMeasure.MILLILITERS, org1);
//        Product prod2 = new Product("prod2", coords2, 320, "1f",
//                1, UnitOfMeasure.MILLILITERS, org2);
//
//        CollectionManager collection = new CollectionManager();
//        collection.addToCollection(prod1);
//        collection.addToCollection(prod2);
//
        FileManager fileManager = new FileManager(fileName);
//        fileManager.writeCollection(collection.getProductCollection());

        CollectionManager collection = fileManager.readCollection();

        CommandManager commandManager = new CommandManager(collection, fileManager);
        commandManager.executeFromCommandLine();

    }
}