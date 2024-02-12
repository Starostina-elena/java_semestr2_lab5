package models;

import java.util.Objects;

public class Product {

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price; //Значение поля должно быть больше 0
    private String partNumber; //Длина строки не должна быть больше 45, Поле может быть null
    private float manufactureCost;
    private UnitOfMeasure unitOfMeasure; //Поле не может быть null
    private Organization manufacturer; //Поле не может быть null

    private static long currentId = 1;

    public Product(String name, Coordinates coordinates, float price, String partNumber,
                   float manufactureCost, UnitOfMeasure unitOfMeasure, Organization manufacturer) {
        this.setName(name);
        this.setCoordinates(coordinates);
        creationDate = java.time.LocalDate.now();
        this.setPrice(price);
        this.setPartNumber(partNumber);
        this.setManufactureCost(manufactureCost);
        this.setUnitOfMeasure(unitOfMeasure);
        this.setManufacturer(manufacturer);
        id = currentId++;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be empty");
        }
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) throws IllegalArgumentException {
        if (coordinates == null) {
            throw new IllegalArgumentException("coordinates cannot be null");
        }
        this.coordinates = coordinates;
    }

    public java.time.LocalDate getCreationDate() {
        return creationDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) throws IllegalArgumentException {
        if (price > 0) {
            this.price = price;
        }
        else {
            throw new IllegalArgumentException("price should be more than 0");
        }
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) throws IllegalArgumentException {
        if (partNumber == null || partNumber.length() <= 45) {
            this.partNumber = partNumber;
        }
        else {
            throw new IllegalArgumentException("length of partNumber should not be bigger than 45");
        }
    }

    public float getManufactureCost() {
        return manufactureCost;
    }

    public void setManufactureCost(float manufactureCost){
        this.manufactureCost = manufactureCost;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) throws IllegalArgumentException {
        if (unitOfMeasure == null) {
            throw new IllegalArgumentException("unitOfMeasure cannot be null");
        }
        this.unitOfMeasure = unitOfMeasure;
    }

    public Organization getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Organization manufacturer) throws IllegalArgumentException {
        if (manufacturer == null) {
            throw new IllegalArgumentException("manufacturer cannot be null");
        }
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return id == product.id && Float.compare(price, product.price) == 0 && Float.compare(manufactureCost, product.manufactureCost) == 0 && Objects.equals(name, product.name) && Objects.equals(coordinates, product.coordinates) && Objects.equals(creationDate, product.creationDate) && Objects.equals(partNumber, product.partNumber) && unitOfMeasure == product.unitOfMeasure && Objects.equals(manufacturer, product.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, price, partNumber, manufactureCost, unitOfMeasure, manufacturer);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", price=" + price +
                ", partNumber='" + partNumber + '\'' +
                ", manufactureCost=" + manufactureCost +
                ", unitOfMeasure=" + unitOfMeasure +
                ", manufacturer=" + manufacturer +
                '}';
    }
}