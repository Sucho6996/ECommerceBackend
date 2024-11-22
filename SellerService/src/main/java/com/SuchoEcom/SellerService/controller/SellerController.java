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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Seller> internalGetSeller(@RequestHeader("Authorization") String authToken){
        String token=authToken.substring(7);
        String name= jwtService.extractUserName(token);
        return service.getSeller(name);
    }

    @GetMapping("/find/{sellerName}")
    public ResponseEntity<List<Product>> sellerView(@RequestHeader("Authorization") String authToken){
        String token=authToken.substring(7);
        String sellerName= jwtService.extractUserName(token);
        return service.sellerView(sellerName);
    }


    @PostMapping("/signin")
    public ResponseEntity<Map<String,String>> signIn(@RequestBody Seller seller){
        return service.addSeller(seller);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Seller seller){
        Map<String,String> response=new HashMap<>();
        Authentication auth=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(seller.getSellername(),seller.getPassword()));
        if (auth.isAuthenticated()){
            response.put("message",jwtService.generateToken(seller.getSellername()));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else {
            response.put("message","Invalid credentials!!!");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Map<String,String>>  update(@RequestBody Seller seller){
        return service.update(seller);
    }
    @PostMapping("internal/update")
    public ResponseEntity<Map<String,String>> internalUpdate
            (@RequestParam("name") String name, @RequestParam("amount") BigDecimal amount){
        return service.internalUpdate(name,amount);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String,String>> addProduct
            (@RequestPart ProductDetails product,@RequestPart MultipartFile imageFile){
        return service.addProduct(product,imageFile);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteProduct(@PathVariable int id){
        return service.deleteProduct(id);
    }

    @PutMapping("/modify")
    public ResponseEntity<Map<String,String>> updateProduct
            (@RequestPart Product product,@RequestPart MultipartFile imageFile){
        return service.UpdateProduct(product,imageFile);
    }
}
