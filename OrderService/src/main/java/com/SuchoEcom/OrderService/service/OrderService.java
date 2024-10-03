package com.SuchoEcom.OrderService.service;


import com.SuchoEcom.OrderService.controller.CustomerFeign;
import com.SuchoEcom.OrderService.controller.ProductFeign;
import com.SuchoEcom.OrderService.controller.SellerFeign;
import com.SuchoEcom.OrderService.model.*;
import com.SuchoEcom.OrderService.repository.CartRepo;
import com.SuchoEcom.OrderService.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    CartRepo cartRepo;
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    CustomerFeign customerFeign;
    @Autowired
    ProductFeign productFeign;
    @Autowired
    SellerFeign sellerFeign;

    public ResponseEntity<List<Orders>> viewOrders(String id) {
        List<Orders> orders =orderRepo.findAllByphoneNo(id);
        if(!orders.isEmpty())
            return new ResponseEntity<>(orders, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<Cart>> viewCarts(String id) {

        List<Cart> orders =cartRepo.findAllByphoneNo(id);
        if(!orders.isEmpty())
            return new ResponseEntity<>(orders, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Orders> viewOrder(int id) {
        Optional<Orders> orders =orderRepo.findById(id);
        if(!orders.isEmpty())
            return new ResponseEntity<>(orders.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Cart> viewCart(int id) {
        Optional<Cart> orders =cartRepo.findById(id);
        if(!orders.isEmpty())
            return new ResponseEntity<>(orders.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    public ResponseEntity<String> saveOder(ProductDetails productDetails) {
        Orders order=new Orders();
        order.setProductId(productDetails.getProductId());
        order.setPhoneNo(productDetails.getPhoneNo());
        order.setSellerName(productDetails.getSellerName());
        order.setProductPrice(productDetails.getProductPrice());

        orderRepo.save(order);
        return new ResponseEntity<>("Successfully Ordered...",HttpStatus.CREATED);
    }

    public ResponseEntity<String> saveCart(ProductDetails productDetails) {
        Cart cart=new Cart();
        cart.setProductId(productDetails.getProductId());
        cart.setPhoneNo(productDetails.getPhoneNo());
        cart.setSellerName(productDetails.getSellerName());
        cart.setProductPrice(productDetails.getProductPrice());

        cartRepo.save(cart);
        return new ResponseEntity<>("Successfully Added!!!",HttpStatus.CREATED);
    }

    public ResponseEntity<Users> getUser(String num) {
        return new ResponseEntity<>(customerFeign.getUserDetails(num).getBody(),HttpStatus.OK);
    }

    public ResponseEntity<Seller> getSeller(String name) {
        return new ResponseEntity<>(sellerFeign.getSeller(name).getBody(),HttpStatus.OK);
    }

    public ResponseEntity<Product> getProduct(int pId) {
        return new ResponseEntity<>(productFeign.findById(pId).getBody(),HttpStatus.OK);
    }
}
