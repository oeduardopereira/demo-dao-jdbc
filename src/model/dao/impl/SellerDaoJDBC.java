package model.dao.impl;

import java.sql.*;

import db.DB;
import model.dao.DepartmentDAO;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDAO {

    Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

        PreparedStatement pst = null;

        try{
            pst = conn.prepareStatement("INSERT INTO seller " +
                  "(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                    "VALUES " +
                    "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, obj.getName());
            pst.setString(2, obj.getEmail());
            pst.setDate(3,new java.sql.Date(obj.getBirthDate().getTime()));
            pst.setDouble(4, obj.getBaseSalary());
            pst.setInt(5, obj.getDepartment().getId());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = pst.getGeneratedKeys();
                if ( rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }else {
                    throw new SQLException("Unexpected error! 0 rows affected!");
                }
                DB.closeResultSet(rs);
            }

        }catch (SQLException e ){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(pst);
        }
    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Department dep = instantiateDepartment(rs);
                return instantiateSeller(rs, dep);
            }
           return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
        return null;
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("Id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setDepartment(dep);
        return obj;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        return new Department(rs.getString("DepName"), rs.getInt("DepartmentId"));
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "ORDER BY Name");
            rs = ps.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Seller seller = instantiateSeller(rs, dep);
                list.add(seller);

            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
        return null;
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? "
                    + "ORDER BY Name");
            ps.setInt(1, department.getId());
            rs = ps.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while (rs.next()) {
                Department dep = map.get(rs.getInt("DepartmentId"));

                if (dep == null){
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Seller seller = instantiateSeller(rs, dep);
                list.add(seller);

            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
        return null;
    }
}
