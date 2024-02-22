package models;

import java.util.Objects;

public class Organization {

    private Long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String fullName; //Поле может быть null
    private Integer employeesCount; //Поле может быть null, Значение поля должно быть больше 0

    private static Long currentId = 1L;

    public Organization(String name, String fullName, Integer employeesCount) {
        this.setName(name);
        this.setFullName(fullName);
        this.setEmployeesCount(employeesCount);
        id = currentId++;
    }

    public Long getId() {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(Integer employeesCount) {
        if (employeesCount == null || employeesCount > 0) {
            this.employeesCount = employeesCount;
        }
        else {
            throw new IllegalArgumentException("employeesCount should be more than 0");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization that)) return false;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(fullName, that.fullName) && Objects.equals(employeesCount, that.employeesCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fullName, employeesCount);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", employeesCount=" + employeesCount +
                '}';
    }

}
