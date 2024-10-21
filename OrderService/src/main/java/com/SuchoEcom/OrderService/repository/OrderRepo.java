package com.SuchoEcom.OrderService.repository;


import com.SuchoEcom.OrderService.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Orders,Integer> {
    Optional<Orders> findByphoneNo(String id);

    List<Orders> findAllByphoneNo(String id);

    Orders findByrazorpayOrderId(String razorpayId);
}
