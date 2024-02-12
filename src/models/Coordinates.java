package models;

import java.util.Objects;

public class Coordinates {

    private double x;
    private float y; //Максимальное значение поля: 113

    public Coordinates(double x, float y) {
        this.setX(x);
        this.setY(y);
    }

    public double getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(float y) throws IllegalArgumentException {
        if (y <= 113) {
            this.y = y;
        }
        else {
            throw new IllegalArgumentException("y should be less or equal 113");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates that)) return false;
        return Double.compare(x, that.x) == 0 && Float.compare(y, that.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
