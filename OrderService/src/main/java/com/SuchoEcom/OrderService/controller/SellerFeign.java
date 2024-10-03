package com.SuchoEcom.OrderService.controller;

import com.SuchoEcom.OrderService.model.Seller;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("SELLER")
public interface SellerFeign {
    @GetMapping("/seller/{sellername}")
    public ResponseEntity<Seller> getSeller(@PathVariable("sellername") String  name);
}
