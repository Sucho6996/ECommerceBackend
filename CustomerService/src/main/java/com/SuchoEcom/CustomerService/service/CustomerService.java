package com.SuchoEcom.CustomerService.service;


import com.SuchoEcom.CustomerService.controller.OrderFeign;
import com.SuchoEcom.CustomerService.controller.ProductFeign;
import com.SuchoEcom.CustomerService.model.*;
import com.SuchoEcom.CustomerService.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    ProductFeign feign;

    @Autowired
    OrderFeign orderFeign;

    @Autowired
    UserRepo repo;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    public ResponseEntity<List<Product>> findAll() {
        return new ResponseEntity<>(feign.findAll().getBody(), HttpStatus.FOUND);
    }

    public ResponseEntity<Product> findById(Integer id) {
        //Product product=feign.findById(id).getBody();
        return new ResponseEntity<>(feign.findById(id).getBody(),HttpStatus.OK);
    }

    public ResponseEntity<List<Product>> findByKeyword(String keyword) {
        return new ResponseEntity<>(feign.findByKeyword(keyword).getBody(),HttpStatus.OK);
    }


    public ResponseEntity<String> order(Integer id, String name, BigDecimal price, String p) {
        ProductDetails productDetails=new ProductDetails();
        productDetails.setProductId(id);
        productDetails.setSellerName(name);
        productDetails.setProductPrice(price);
        productDetails.setPhoneNo(p);
        return orderFeign.saveOrder(productDetails);

    }

    public ResponseEntity<String> cart(Integer id, String name,BigDecimal price, String p) {
        ProductDetails productDetails=new ProductDetails();
        productDetails.setProductId(id);
        productDetails.setSellerName(name);
        productDetails.setProductPrice(price);
        productDetails.setPhoneNo(p);
        return orderFeign.saveCart(productDetails);

    }

    public ResponseEntity<Users> getUserDetails(String ph) {
        return new ResponseEntity<>(repo.findAllByphoneNo(ph),HttpStatus.OK);
    }

    public ResponseEntity<String> saveUser(Users user) {
        repo.save(user);
        return new ResponseEntity<>("Added!!",HttpStatus.CREATED);
    }

    public ResponseEntity<List<Orders>> getOrders(String ph) {
        return new ResponseEntity<>(orderFeign.viewOrders(ph).getBody(),HttpStatus.OK);
    }

    public ResponseEntity<List<Cart>> getCarts(String ph) {
        return new ResponseEntity<>(orderFeign.viewCarts(ph).getBody(),HttpStatus.OK);
    }

    public ResponseEntity<Orders> getOrder(int id) {
        return new ResponseEntity<>(orderFeign.viewOrder(id).getBody(),HttpStatus.OK);
    }

    public ResponseEntity<Cart> getCart(int id) {
        return new ResponseEntity<>(orderFeign.viewCart(id).getBody(),HttpStatus.OK);
    }

    public ResponseEntity<String> addUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return new ResponseEntity<>("Account Created",HttpStatus.CREATED);
    }
}
