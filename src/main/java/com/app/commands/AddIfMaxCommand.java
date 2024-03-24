package com.app.commands;

import com.app.managers.CollectionManager;
import com.app.models.Coordinates;
import com.app.models.Organization;
import com.app.models.Product;
import com.app.models.UnitOfMeasure;

import java.util.*;

public class AddIfMaxCommand implements Command {

    private CollectionManager collectionManager;

    public AddIfMaxCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "adds new element if this element is bigger than max element in collection. Pattern: " +
                "add_if_max (String)name (Integer)price(may be null, use empty string) (String)partNumber " +
                "(Integer)manufactureCost";
    }

    public void execute(String[] arguments) {
        try {
            Integer price;
            try {
                if (arguments[2].isBlank()) {
                    price = null;
                } else {
                    price = Integer.parseInt(arguments[2]);
                }
            } catch (NumberFormatException e) {
                System.out.println("price is not correct, try again" + arguments[2]);
                return;
            }
            try {
                Integer.parseInt(arguments[4]);
            } catch (NumberFormatException e) {
                System.out.println("manufactureCost is not correct, try again");
                return;
            }
            Scanner in = new Scanner(System.in);
            Coordinates coords;
            long x;
            double y;
            while (true) {
                try {
                    System.out.println("Enter coordinates (long)x");
                    System.out.print("> ");
                    x = in.nextLong();
                    System.out.println("Enter coordinates (double)y");
                    System.out.print("> ");
                    y = in.nextDouble();
                    coords = new Coordinates(x, y);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Wrong coordinates, try again");
                    in.nextLine();
                }
            }
            coords = new Coordinates(1, 2);
            ArrayList<String> unitOfMeasures = new ArrayList<>();
            System.out.println("Enter one of unit of measure:");
            for (UnitOfMeasure c : UnitOfMeasure.values()) {
                System.out.println(c);
                unitOfMeasures.add(c.name());
            }
            System.out.print("> ");
            in.nextLine();
            String unitOfMeasure = in.nextLine();
            while (!unitOfMeasures.contains(unitOfMeasure) & !unitOfMeasure.isBlank()) {
                System.out.println("Wrong unit of measure, please try again:");
                System.out.print("> ");
                unitOfMeasure = in.nextLine();
            }
            UnitOfMeasure resUnitOfMeasure;
            if (unitOfMeasure.isBlank()) {
                resUnitOfMeasure = null;
            } else {
                resUnitOfMeasure = UnitOfMeasure.valueOf(unitOfMeasure);
            }
            Organization org;
            while (true) {
                try {
                    System.out.println("Enter organization (String)name");
                    System.out.print("> ");
                    String name = in.nextLine();
                    System.out.println("Enter organization (String)fullName. Press enter to leave this field empty");
                    System.out.print("> ");
                    String fullName = in.nextLine();
                    if (fullName.isBlank()) {
                        fullName = null;
                    }
                    Integer employeesCount;
                    while (true) {
                        try {
                            System.out.println("Enter organization (Integer)employeesCount. Press enter to leave this field empty");
                            System.out.print("> ");
                            String inEmployeesCount = in.nextLine();
                            if (inEmployeesCount.isBlank()) {
                                employeesCount = null;
                            } else {
                                employeesCount = Integer.parseInt(inEmployeesCount);
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Wrong employeesCount. Please try again:");
                        }
                    }
                    org = new Organization(name, fullName, employeesCount);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e + ". Please try again");
                }
            }

            Product product = new Product(arguments[1], coords, price, arguments[3],
                    Integer.parseInt(arguments[4]), resUnitOfMeasure, org);
            Product max_product = collectionManager.getProductCollection().getFirst();
            for (Product c : collectionManager.getProductCollection()) {
                if (c.compareTo(max_product) > 0) {
                    max_product = c;
                }
            }
            if (product.compareTo(max_product) > 0) {
                collectionManager.addToCollection(product);
                System.out.println("object was successfully added");
                System.out.println(product);
            } else {
                System.out.println("object is not max, it wasn't added");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e + ". Please try again");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect number of arguments for add_if_max command. Please try again");
        }
    }

}
