package model.dao.impl;

import db.DB;
import model.dao.DepartmentDAO;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDAO {

    Connection conn;

    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;
    }
    @Override
    public void insert(Department obj) {

        PreparedStatement st = null;

        try{

            st = conn.prepareStatement("INSERT INTO department " +
                    "(Id, Name) " +
                    "VALUES " +
                    "(?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, obj.getId());
            st.setString(2, obj.getName());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0 ){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                    System.out.println("Inserted! " + rowsAffected + " rows affected.");
                }else {
                    throw  new SQLException("Unexpected error! O rows affected.");
                }
                DB.closeResultSet(rs);
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void update(Department obj) {

        PreparedStatement st = null;

        try{
            st = conn.prepareStatement("UPDATE department Name = ? WHERE Id = ?");

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            st.executeUpdate();
        }catch (SQLException e ){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void deleteById(Integer id) {

        PreparedStatement st = null;

        try{

            st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
            st.setInt(1, id);
            st.executeUpdate();

        }catch (SQLException e ){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT department.* "
                    + "FROM department "
                    + "WHERE department.Id = ?");

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
               return instantiateDepartment(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
        return null;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        return new Department(rs.getString("Name"), rs.getInt("Id"));
    }

    @Override
    public List<Department> findAll() {

        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = conn.prepareStatement(" SELECT * FROM department ORDER BY Id");
            rs = st.executeQuery();

            List<Department> list = new ArrayList<>();

            while (rs.next()){
                Department dep  = new Department(rs.getString("Name"), rs.getInt("Id"));
                list.add(dep);
            }
            return list;

        }catch (SQLException e ){
            e.printStackTrace();
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return null;
    }
}
