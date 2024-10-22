package com.SuchoEcom.SellerService.controller;


import com.SuchoEcom.SellerService.model.Product;
import com.SuchoEcom.SellerService.model.ProductDetails;
import com.SuchoEcom.SellerService.model.Seller;
import com.SuchoEcom.SellerService.service.JwtService;
import com.SuchoEcom.SellerService.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService service;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @GetMapping("/seller/{sellername}")
    public ResponseEntity<Seller> getSeller(@PathVariable("sellername") String  name){
        return service.getSeller(name);
    }

    @GetMapping("internal/seller/{sellername}")
    public ResponseEntity<Seller> internalGetSeller(@PathVariable("sellername") String  name){
        return service.getSeller(name);
    }

    @GetMapping("/find/{sellerName}")
    public ResponseEntity<List<Product>> sellerView(@PathVariable String sellerName){
        return service.sellerView(sellerName);
    }


    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody Seller seller){
        return service.addSeller(seller);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Seller seller){
        Authentication auth=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(seller.getSellername(),seller.getPassword()));
        if (auth.isAuthenticated()){
            return new ResponseEntity<>(jwtService.generateToken(seller.getSellername()),HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Invalid credentials!!!", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody Seller seller){
        return service.update(seller);
    }
    @PostMapping("internal/update")
    public ResponseEntity<String> internalUpdate
            (@RequestParam("name") String name, @RequestParam("amount") BigDecimal amount){
        return service.internalUpdate(name,amount);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct
            (@RequestPart ProductDetails product,@RequestPart MultipartFile imageFile){
        return service.addProduct(product,imageFile);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        return service.deleteProduct(id);
    }

    @PutMapping("/modify")
    public ResponseEntity<String> updateProduct
            (@RequestPart Product product,@RequestPart MultipartFile imageFile){
        return service.UpdateProduct(product,imageFile);
    }
}
