package com.SuchoEcom.SellerService.repo;

import com.SuchoEcom.SellerService.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository<Seller,String> {

    Seller findBysellername(String sellername);
}
