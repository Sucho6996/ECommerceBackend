package com.SuchoEcom.CustomerService.controller;


import com.SuchoEcom.CustomerService.model.*;
import com.SuchoEcom.CustomerService.service.JwtService;
import com.SuchoEcom.CustomerService.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class CustomerController {

    @Autowired
    CustomerService service;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;


    /*This API is for Signup*/
    @PostMapping("/signup")
    public ResponseEntity<Map<String,String>> signIn(@RequestBody Users user){
        return service.addUser(user);
    }

    /*API for login*/
    @PostMapping("/login")
    public ResponseEntity<Map<String,String>>login(@RequestBody Users user){
        Map<String,String> response=new HashMap<>();
        Authentication auth=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if (auth.isAuthenticated()){
            response.put("token",jwtService.generateToken(user.getUsername()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            response.put("token","Invalid credentials!!!");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }


    /*API to get all the available product*/
    @GetMapping("/findAll")
    public ResponseEntity<List<Product>> findAll(){
        return service.findAll();
    }

    /*Use this after user clicking on a product*/
    @GetMapping("/find/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id){
        return service.findById(id);
    }

    /*Use for Searching for a product*/
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Product>> findByKeyword(@PathVariable String keyword){
        return service.findByKeyword(keyword);
    }

    /*Use for placing an order*/
    @GetMapping("/order")
    public ResponseEntity<Map<String,String>>order
            (@RequestParam("pId") Integer id,
             @RequestHeader("Authorization") String authToken){
        String token=authToken.substring(7);
        String p= jwtService.extractUserName(token);
        return service.order(id,p);
    }

    /*Use for add to cart*/
    @GetMapping("/cart")
    public ResponseEntity<Map<String,String>> cart
            (@RequestParam("pId") Integer id,
             @RequestHeader("Authorization") String authToken){
        String token=authToken.substring(7);
        String p= jwtService.extractUserName(token);
        return service.cart(id,p);
    }

    /*Use to show users their details*/
    @GetMapping("/view")
    public ResponseEntity<Users> getUserDetails(@RequestHeader("Authorization") String authToken){
        String token=authToken.substring(7);
        String ph=jwtService.extractUserName(token);
        return service.getUserDetails(ph);
    }

    /*To see your orders*/
    @GetMapping("/orders")
    public ResponseEntity<List<Orders>> getOrders(@RequestHeader("Authorization") String authToken){
        String token=authToken.substring(7);
        String ph=jwtService.extractUserName(token);
        return service.getOrders(ph);
    }

    /*To see your cart*/
    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> getCarts(@RequestHeader("Authorization") String authToken){
        String token=authToken.substring(7);
        String ph=jwtService.extractUserName(token);
        return service.getCarts(ph);
    }

    /*Get details of a particular order*/
    @GetMapping("/order/{id}")
    public ResponseEntity<Orders> getOrder(@PathVariable("id") int id){
        return service.getOrder(id);
    }

    /*Get details of a particular cart item*/
    @GetMapping("/cart/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable("id") int id){
        return service.getCart(id);
    }

    /*
    Ayse hi bana diya
    kyu ki sexy lag raha tha
    */
    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody Users user){
        return service.saveUser(user);
    }
}
