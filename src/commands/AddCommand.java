package commands;

import managers.CollectionManager;
import models.Coordinates;
import models.Organization;
import models.Product;
import models.UnitOfMeasure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AddCommand implements Command {

    private CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "adds an element to collection. " +
                "Pattern: add (String)name (Integer)price(may be null, use empty string) (String)partNumber (Integer)manufactureCost";
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
                    x = in.nextLong();
                    System.out.println("Enter coordinates (double)y");
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
            in.nextLine();
            String unitOfMeasure = in.nextLine();
            while (!unitOfMeasures.contains(unitOfMeasure) & !unitOfMeasure.isBlank()) {
                System.out.println("Wrong unit of measure, please try again:");
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
                    String name = in.nextLine();
                    System.out.println("Enter organization (String)fullName. Press enter to leave this field empty");
                    String fullName = in.nextLine();
                    if (fullName.isBlank()) {
                        fullName = null;
                    }
                    Integer employeesCount;
                    while (true) {
                        try {
                            System.out.println("Enter organization (Integer)employeesCount. Press enter to leave this field empty");
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
            collectionManager.addToCollection(product);
            System.out.println("object was successfully added");
            System.out.println(product);
        } catch (IllegalArgumentException e) {
            System.out.println(e + ". Please try again");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect number of arguments for add command. Please try again");
        }
    }
}
