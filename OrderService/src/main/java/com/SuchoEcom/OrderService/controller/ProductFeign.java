package com.SuchoEcom.OrderService.controller;

import com.SuchoEcom.OrderService.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("PRODUCT")
public interface ProductFeign {
    @GetMapping("product/find/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id);

    @PutMapping("product/modify")
    public String modifyProduct(@RequestBody Product product);
}
