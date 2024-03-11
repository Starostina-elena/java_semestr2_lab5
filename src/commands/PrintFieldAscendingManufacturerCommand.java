package commands;

import managers.CollectionManager;
import models.Organization;
import models.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintFieldAscendingManufacturerCommand implements Command {

    private CollectionManager collectionManager;

    public PrintFieldAscendingManufacturerCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "shows all manufacturers in ascending orders";
    }

    public void execute(String[] arguments) {
        List<Organization> orgList = new ArrayList<>();
        for (Product c : collectionManager.getProductCollection()) {
            orgList.add(c.getManufacturer());
        }
        Collections.sort(orgList);
        for (Organization c : orgList) {
            System.out.println(c);
        }
    }

}
