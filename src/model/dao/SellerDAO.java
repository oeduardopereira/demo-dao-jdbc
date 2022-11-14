package model.dao;

import model.entities.Department;
import model.entities.Seller;

import java.util.List;

public interface SellerDAO {

    void insert(Seller obj);
    void update(Seller obj);
    void deleteById(Integer id);
    Seller findByI(Integer id);
    List<Seller> findAll();

}
