package com.SuchoEcom.ProductService.V1.repo;



import com.SuchoEcom.ProductService.V1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Query("SELECT p from Product p WHERE " +
            "LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.productDesc) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.productBrand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.productCat) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> findByKeyword(String keyword);
}
