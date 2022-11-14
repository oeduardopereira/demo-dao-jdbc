package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDAO;

public class Program2 {

    public static void main(String[] args) {

        DepartmentDAO departmentDAO = DaoFactory.createDepartmentDAO();

    }

}
