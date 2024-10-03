package com.SuchoEcom.OrderService.repository;


import com.SuchoEcom.OrderService.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {

    Optional<Cart> findByphoneNo(String id);

    List<Cart> findAllByphoneNo(String id);
}
