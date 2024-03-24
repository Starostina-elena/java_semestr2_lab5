package com.app.commands;

import com.app.managers.CollectionManager;
import com.app.models.Coordinates;
import com.app.models.Organization;
import com.app.models.Product;
import com.app.models.UnitOfMeasure;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateCommand implements Command {

    private CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public String description() {
        return "update product by it's id. Pattern: update (long)id";
    }

    public void execute(String[] arguments) {
        try {
            Product product = collectionManager.getById(Integer.parseInt(arguments[1]));
            System.out.println(product);
            System.out.println("Enter new name");
            Scanner in = new Scanner(System.in);
            while (true) {
                try {
                    String name = in.nextLine();
                    product.setName(name);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e + ". Please try again");
                }
            }
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
            product.setCoordinates(coords);
            System.out.println("Enter new price (integer)");
            while (true) {
                try {
                    Integer price = in.nextInt();
                    product.setPrice(price);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Price should be integer. Please try again");
                    in.nextLine();
                } catch (IllegalArgumentException e) {
                    System.out.println(e + ". Please try again");
                }
            }
            System.out.println("Enter new partNumber");
            in.nextLine();
            while (true) {
                try {
                    String partNumber = in.nextLine();
                    product.setPartNumber(partNumber);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e + ". Please try again");
                }
            }
            System.out.println("Enter new manufactureCost (integer)");
            while (true) {
                try {
                    Integer manufactureCost = in.nextInt();
                    product.setManufactureCost(manufactureCost);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("manufactureCost should be integer. Please try again");
                    in.nextLine();
                } catch (IllegalArgumentException e) {
                    System.out.println(e + ". Please try again");
                }
            }
            ArrayList<String> unitOfMeasures = new ArrayList<>();
            System.out.println("Enter one of unit of measure:");
            for (UnitOfMeasure c : UnitOfMeasure.values()) {
                System.out.println(c);
                unitOfMeasures.add(c.name());
            }
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
            product.setUnitOfMeasure(resUnitOfMeasure);
            Organization org;
            in.nextLine();
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
            product.setManufacturer(org);
            System.out.println("Updated successfully");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect number of arguments for update command. Please try again");
        } catch (NumberFormatException e) {
            System.out.println("id is not integer. Please try again");
        } catch (IllegalArgumentException e) {
            System.out.println(e + ". Please try again");
        }
    }

}
