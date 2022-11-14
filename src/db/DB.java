package db;

import java.sql.*;

public class DB{

    private static Connection conn = null;

    public static Connection getConn() {
        try {
            if (conn == null) {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbcdatabase", "root", "eduardop$filho");
            }
        }catch (SQLException e ){
            e.printStackTrace();
        }
            return conn;
    }

    public static void closeConn(){
        if (conn != null){
            try{
                conn.close();
            }catch (SQLException e ){
                e.printStackTrace();
            }
        }
    }

    public static void closeStatement(Statement st){
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
              e.printStackTrace();
            }
        }
    }
    }
