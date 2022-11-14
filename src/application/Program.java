package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.entities.Seller;


public class Program {
    public static void main(String[] args) {


        SellerDAO sellerDAO = DaoFactory.createSellerDAO();

        System.out.println("=== TEST 1: ===");
        Seller seller = sellerDAO.findById(3);
        System.out.println(seller);
        System.out.println();

        DB.closeConn();

    }
}