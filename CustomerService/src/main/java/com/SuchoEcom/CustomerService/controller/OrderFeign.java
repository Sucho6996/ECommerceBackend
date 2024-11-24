package com.SuchoEcom.CustomerService.controller;


import com.SuchoEcom.CustomerService.model.Cart;
import com.SuchoEcom.CustomerService.model.Orders;
import com.SuchoEcom.CustomerService.model.ProductDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("ORDER")
public interface OrderFeign {
    @GetMapping("order&cart/view/orders/{id}")
    public ResponseEntity<List<Orders>> viewOrders(@PathVariable String id);

    @GetMapping("order&cart/view/carts/{id}")
    public ResponseEntity<List<Cart>> viewCarts(@PathVariable String id);

    @GetMapping("order&cart/view/orderDetails/{id}")
    public ResponseEntity<Orders> viewOrder(@PathVariable int id);

    @GetMapping("order&cart/view/cartDetails/{id}")
    public ResponseEntity<Cart> viewCart(@PathVariable int id);
    @PostMapping("order&cart/create/order")
    public ResponseEntity<String> saveOrder(@RequestBody ProductDetails productDetails);

    @PostMapping("order&cart/save/cart")
    public ResponseEntity<String> saveCart(@RequestBody ProductDetails productDetails);
}
