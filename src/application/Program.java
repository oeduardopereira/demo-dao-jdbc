package application;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.sql.SQLException;
import java.util.Date;

public class Program {
    public static void main(String[] args) {


            SellerDAO sellerDAO = DaoFactory.createSellerDAO();
            Seller seller = sellerDAO.findById(3);
            System.out.println(seller);
            DB.closeConn();

    }
}