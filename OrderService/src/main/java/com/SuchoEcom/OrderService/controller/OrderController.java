package com.SuchoEcom.OrderService.controller;


import com.SuchoEcom.OrderService.model.*;
import com.SuchoEcom.OrderService.service.OrderService;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order&cart")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/view/orders/{id}")
    public ResponseEntity<List<Orders>> viewOrders(@PathVariable String id){
        return orderService.viewOrders(id);
    }

    @GetMapping("/view/carts/{id}")
    public ResponseEntity<List<Cart>> viewCarts(@PathVariable String id){
        return orderService.viewCarts(id);
    }
    @GetMapping("/view/orderDetails/{id}")
    public ResponseEntity<Orders> viewOrder(@PathVariable int id){
        return orderService.viewOrder(id);
    }

    @GetMapping("/view/cartDetails/{id}")
    public ResponseEntity<Cart> viewCart(@PathVariable int id){
        return orderService.viewCart(id);
    }

    @PostMapping("/create/order")
    public ResponseEntity<Map<String,String>> saveOrder(@RequestBody ProductDetails productDetails) throws RazorpayException {
        return orderService.saveOder(productDetails);
    }

    /*Call this after successful payment*/
    @PostMapping("/paymentCallback")
    public ResponseEntity<Map<String,String>> updateOrder(@RequestParam String response) throws RazorpayException {
        return orderService.updateOrder(response);
    }

    @PostMapping("/save/cart")
    public ResponseEntity<String> saveCart(@RequestBody ProductDetails productDetails){
        return orderService.saveCart(productDetails);
    }

    /*See the buyer details from order*/
    @GetMapping("/user/{phNo}")
    public ResponseEntity<Users> getUser(@PathVariable("phNo") String num){
        return orderService.getUser(num);
    }

    /*See the seller details from order*/
    @GetMapping("/seller/{sellername}")
    public ResponseEntity<Seller> getSeller(@PathVariable("sellername") String name){
        return orderService.getSeller(name);
    }

    /*See the product details from order*/
    @GetMapping("/product/{pId}")
    public ResponseEntity<Product> getProduct(@PathVariable int pId){
        return orderService.getProduct(pId);
    }

}
