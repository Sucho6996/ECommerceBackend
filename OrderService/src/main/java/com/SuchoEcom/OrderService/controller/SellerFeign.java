package com.SuchoEcom.OrderService.controller;

import com.SuchoEcom.OrderService.model.Seller;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@FeignClient("SELLER")
public interface SellerFeign {
    @GetMapping("/seller/{sellername}")
    public ResponseEntity<Seller> getSeller(@PathVariable("sellername") String  name);
    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody Seller seller);
    @GetMapping("/seller/internal/seller/{sellername}")
    public ResponseEntity<Seller> internalGetSeller(@PathVariable("sellername") String  name);

    @PostMapping("seller/internal/update")
    public ResponseEntity<String> internalUpdate
            (@RequestParam("name") String name, @RequestParam("amount") BigDecimal amount);
}
