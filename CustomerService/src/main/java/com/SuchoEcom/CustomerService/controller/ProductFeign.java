package com.SuchoEcom.CustomerService.controller;



import com.SuchoEcom.CustomerService.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("PRODUCT")
public interface ProductFeign {


    @GetMapping("product/findAll")
    public ResponseEntity<List<Product>> findAll();

    @GetMapping("product/find/{id}") public ResponseEntity<Product> findById(@PathVariable Integer id);

    @GetMapping("product/search/{keyword}") public ResponseEntity<List<Product>> findByKeyword(@PathVariable String keyword);

}
