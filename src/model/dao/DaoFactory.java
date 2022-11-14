package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

    public static SellerDAO createSellerDAO() {
        return new SellerDaoJDBC(DB.getConn());
    }

}
