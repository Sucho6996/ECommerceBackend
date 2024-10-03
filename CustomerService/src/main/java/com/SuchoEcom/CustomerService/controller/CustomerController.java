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
import java.util.List;

@RestController
@RequestMapping("user")
public class CustomerController {

    @Autowired
    CustomerService service;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;


    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody Users user){
        return service.addUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user){
        Authentication auth=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if (auth.isAuthenticated()){
            return new ResponseEntity<>(jwtService.generateToken(user.getUsername()), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Invalid credentials!!!", HttpStatus.UNAUTHORIZED);
    }



    @GetMapping("/findAll")
    public ResponseEntity<List<Product>> findAll(){
        return service.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Product>> findByKeyword(@PathVariable String keyword){
        return service.findByKeyword(keyword);
    }

    @GetMapping("/order")
    public ResponseEntity<String> order
            (@RequestParam("pId") Integer id, @RequestParam("sellerName") String name, @RequestParam("price") BigDecimal price, @RequestParam("phNo") String p){
        return service.order(id,name,price,p);
    }

    @GetMapping("/cart")
    public ResponseEntity<String> cart
            (@RequestParam("pId") Integer id,@RequestParam("sellerName") String name,@RequestParam("price") BigDecimal price,@RequestParam("phNo") String p){
        return service.cart(id,name,price,p);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Users> getUserDetails(@PathVariable("id") String ph){
        return service.getUserDetails(ph);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<List<Orders>> getOrders(@PathVariable("id") String ph){
        return service.getOrders(ph);
    }
    @GetMapping("/carts/{id}")
    public ResponseEntity<List<Cart>> getCarts(@PathVariable("id") String ph){
        return service.getCarts(ph);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Orders> getOrder(@PathVariable("id") int id){
        return service.getOrder(id);
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable("id") int id){
        return service.getCart(id);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody Users user){
        return service.saveUser(user);
    }
}
