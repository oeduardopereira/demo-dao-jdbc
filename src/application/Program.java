package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;


public class Program {
    public static void main(String[] args) {


        SellerDAO sellerDAO = DaoFactory.createSellerDAO();

        System.out.println("=== TEST 1 Seller: find by Id ===");
        Seller seller = sellerDAO.findById(3);
        System.out.println(seller);
        System.out.println();

        System.out.println("=== TEST 2 Seller: find by Department Id ===");
        Department dep = new Department(null, 2);
        List<Seller> sellerList = sellerDAO.findByDepartment(dep);
        for (Seller s: sellerList) {
            System.out.println(s);
        }
        System.out.println();

        System.out.println("=== TEST 3 Seller: find All ===");
        sellerList = sellerDAO.findAll();
        for (Seller s: sellerList) {
            System.out.println(s);
        }
        System.out.println();

        System.out.println("=== TEST 1 Seller: find by Id ===");
        Seller s1 = new Seller("Baksa", null, 34000.0, new Date(), "bk@gmail.com", dep);
        sellerDAO.insert(s1);
        System.out.println("Inserted! New seller id: " + s1.getId() );
        System.out.println();

        DB.closeConn();

    }
}