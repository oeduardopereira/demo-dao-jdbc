package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDAO;
import model.entities.Department;

import java.util.ArrayList;
import java.util.List;

public class Program2 {

    public static void main(String[] args) {

        DepartmentDAO departmentDAO = DaoFactory.createDepartmentDAO();

        System.out.println("=== TEST 1 Department: INSERT ===");
        Department dp = new Department("Fashion", null);
        departmentDAO.insert(dp);
        System.out.println();

        System.out.println("=== TEST 2 Department: UPDATE ===");
        dp = new Department("Sports", 6);
        departmentDAO.update(dp);
        System.out.println("Updated!");
        System.out.println();

        System.out.println("=== TEST 3 Department: DELETE ===");
        departmentDAO.deleteById(7);
        System.out.println("Deleted!");
        System.out.println();

        System.out.println("=== TEST 4 Department: Find By ID ===");
        dp = departmentDAO.findById(2);
        System.out.println(dp);
        System.out.println();

        System.out.println("=== TEST 5 Department: Find All ===");
        List<Department> list = new ArrayList<>();
        list = departmentDAO.findAll();
        for (Department p : list) {
            System.out.println(p);
        }
        System.out.println();

    }

}
