package com.SuchoEcom.OrderService.controller;


import com.SuchoEcom.OrderService.model.ProductDetails;
import com.SuchoEcom.OrderService.model.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
@FeignClient("CUSTOMER")
public interface CustomerFeign {

    @GetMapping("/view/{id}")
    public ResponseEntity<Users> getUserDetails(@PathVariable String ph);
    @GetMapping("/order")
    public ResponseEntity<ProductDetails> order
            (@RequestParam("pId") Integer id, @RequestParam("sellerName") String name, @RequestParam("phNo") String p);

    @GetMapping("/cart")
    public ResponseEntity<ProductDetails> cart
            (@RequestParam("pId") Integer id,@RequestParam("sellerName") String name,@RequestParam("phNo") String p);
}
