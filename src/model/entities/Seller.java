package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable {

    private String name;
    private Integer id;
    private Double baseSalary;
    private Date birthDate;
    private String email;
    private Department department;

    public Seller(){}

    public Seller(String name, Integer id, Double baseSalary, Date birthDate, String email, Department department) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
        this.birthDate = birthDate;
        this.email = email;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return id.equals(seller.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Seller{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", baseSalary=" + baseSalary +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                "Department=" + department +
                '}';
    }
}
