package com.SuchoEcom.SellerService.controller;


import com.SuchoEcom.SellerService.model.Product;
import com.SuchoEcom.SellerService.model.ProductDetails;
import com.SuchoEcom.SellerService.model.Seller;
import com.SuchoEcom.SellerService.model.SellerLogin;
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

    /*For new seller to register*/
    @PostMapping("/signup")
    public ResponseEntity<Map<String,String>> signup(@RequestBody Seller seller){
        return service.addSeller(seller);
    }

    /*For existing seller login*/
    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody SellerLogin seller){
        Map<String,String> response=new HashMap<>();
        Authentication auth=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(seller.getUsername(),seller.getPassword()));
        if (auth.isAuthenticated()){
            response.put("message",jwtService.generateToken(seller.getUsername()));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else {
            response.put("message","Invalid credentials!!!");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    /*Seller can see own profile*/
    @GetMapping("/seller")
    public ResponseEntity<Seller> GetSeller(@RequestHeader("Authorization") String authToken){
        String token=authToken.substring(7);
        String name= jwtService.extractUserName(token);
        return service.getSeller(name);
    }

    /*For inter service convo*/
    @GetMapping("internal/seller")
    public ResponseEntity<Seller> internalGetSeller(@RequestHeader("Authorization") String authToken){
        String token=authToken.substring(7);
        String name= jwtService.extractUserName(token);
        return service.getSeller(name);
    }

    /*For seller to see his own products*/
    @GetMapping("/find")
    public ResponseEntity<List<Product>> sellerView(@RequestHeader("Authorization") String authToken){
        String token=authToken.substring(7);
        String sellerName= jwtService.extractUserName(token);
        return service.sellerView(sellerName);
    }


    /*Update product*/
    @PostMapping("/update")
    public ResponseEntity<Map<String,String>>  update(@RequestBody Seller seller){
        return service.update(seller);
    }

    /*For inter service convo*/
    @PostMapping("internal/update")
    public ResponseEntity<Map<String,String>> internalUpdate
            (@RequestParam("name") String name, @RequestParam("amount") BigDecimal amount){
        return service.internalUpdate(name,amount);
    }

    /*Adding new product*/
    @PostMapping("/add")
    public ResponseEntity<Map<String,String>> addProduct
            (@RequestHeader("Authorization") String authToken,@RequestPart ProductDetails product,@RequestPart MultipartFile imageFile){
        String token=authToken.substring(7);
        String name= jwtService.extractUserName(token);
        return service.addProduct(name,product,imageFile);
    }

    /*Delete a product*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>> deleteProduct(@PathVariable int id){
        return service.deleteProduct(id);
    }

    /*Modify existing product*/
    @PutMapping("/modify/{id}")
    public ResponseEntity<Map<String,String>> updateProduct
            (@PathVariable int id,@RequestPart Product product,@RequestPart MultipartFile imageFile){
        return service.UpdateProduct(id,product,imageFile);
    }
}
