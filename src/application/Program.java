package application;

import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args) {

        Department dp = new Department("Books",1 );
        System.out.println(dp);

        Seller s1 = new Seller("Eduardo", 1, 3000.0, new Date(), "edu@gmail.com", dp);
        System.out.println(s1);

        SellerDAO sellerDAO =  DaoFactory.createSellerDAO();
    }
}