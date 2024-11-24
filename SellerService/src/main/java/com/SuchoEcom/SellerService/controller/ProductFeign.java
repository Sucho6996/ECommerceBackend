package com.SuchoEcom.SellerService.controller;


import com.SuchoEcom.SellerService.model.Product;
import com.SuchoEcom.SellerService.model.ProductDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("PRODUCT")
public interface ProductFeign {

    @GetMapping("product/findAll/{sellerName}")
    public ResponseEntity<List<Product>> findAll(@PathVariable String sellerName);

    @GetMapping("product/find/{id}")
    public ResponseEntity<Product> findById(@PathVariable String id);

    @GetMapping("product/find/{id}") public ResponseEntity<Product> findById(@PathVariable Integer id);
    @PostMapping("product/add")
    public String addProduct(@RequestBody ProductDetails product);
    @PutMapping("product/modify")
    public String modifyProduct(@RequestBody Product product);
    @DeleteMapping("product/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id);

}
