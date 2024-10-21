package com.SuchoEcom.OrderService.service;


import com.SuchoEcom.OrderService.controller.CustomerFeign;
import com.SuchoEcom.OrderService.controller.ProductFeign;
import com.SuchoEcom.OrderService.controller.SellerFeign;
import com.SuchoEcom.OrderService.model.*;
import com.SuchoEcom.OrderService.repository.CartRepo;
import com.SuchoEcom.OrderService.repository.OrderRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import jakarta.annotation.PostConstruct;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    CartRepo cartRepo;
    @Autowired
    OrderRepo orderRepo;

    @Autowired
    CustomerFeign customerFeign;
    @Autowired
    ProductFeign productFeign;
    @Autowired
    SellerFeign sellerFeign;

    //For payment gateway
    @Value("${razorpay.key.id}")
    private String razorpayId;
    @Value("${razorpay.key.secret}")
    private String razorpaySecret;

    private RazorpayClient razorpayClient;

    @PostConstruct//Learn
    public void init() throws RazorpayException {
        this.razorpayClient=new RazorpayClient(razorpayId,razorpaySecret);
    }

    public ResponseEntity<List<Orders>> viewOrders(String id) {
        List<Orders> orders =orderRepo.findAllByphoneNo(id);
        if(!orders.isEmpty())
            return new ResponseEntity<>(orders, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<Cart>> viewCarts(String id) {

        List<Cart> orders =cartRepo.findAllByphoneNo(id);
        if(!orders.isEmpty())
            return new ResponseEntity<>(orders, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Orders> viewOrder(int id) {
        Optional<Orders> orders =orderRepo.findById(id);
        if(!orders.isEmpty())
            return new ResponseEntity<>(orders.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Cart> viewCart(int id) {
        Optional<Cart> orders =cartRepo.findById(id);
        if(!orders.isEmpty())
            return new ResponseEntity<>(orders.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    public ResponseEntity<String> saveOder(ProductDetails productDetails) throws RazorpayException {
        JSONObject jobj=new JSONObject();//As Razorpay need json object to work with
        Orders order=new Orders();
        order.setProductId(productDetails.getProductId());
        order.setPhoneNo(productDetails.getPhoneNo());
        order.setSellerName(productDetails.getSellerName());
        order.setProductPrice(productDetails.getProductPrice());

        //For payment gateway call
        jobj.put("amount",order.getProductPrice().intValue()*100);
        jobj.put("currency","INR");
        jobj.put("receipt",order.getPhoneNo());
        Order razorPayOrder=razorpayClient.orders.create(jobj);
        if (razorPayOrder!=null){
            order.setRazorpayOrderId(razorPayOrder.get("id"));
            order.setOrderStatus(razorPayOrder.get("status"));
        }

        orderRepo.save(order);
        return new ResponseEntity<>("Ordered Generated...",HttpStatus.CREATED);
    }

    public ResponseEntity<String> updateOrder(String response) throws RazorpayException {
        String razorpayId=response;
        Orders order=orderRepo.findByrazorpayOrderId(razorpayId);
        order.setOrderStatus("Payment Done");
        orderRepo.save(order);
        return new ResponseEntity<>("Payment Done!!", HttpStatus.OK);
    }

    public ResponseEntity<String> saveCart(ProductDetails productDetails) {
        Cart cart=new Cart();
        cart.setProductId(productDetails.getProductId());
        cart.setPhoneNo(productDetails.getPhoneNo());
        cart.setSellerName(productDetails.getSellerName());
        cart.setProductPrice(productDetails.getProductPrice());

        cartRepo.save(cart);
        return new ResponseEntity<>("Successfully Added!!!",HttpStatus.CREATED);
    }

    public ResponseEntity<Users> getUser(String num) {
        return new ResponseEntity<>(customerFeign.getUserDetails(num).getBody(),HttpStatus.OK);
    }

    public ResponseEntity<Seller> getSeller(String name) {
        return new ResponseEntity<>(sellerFeign.getSeller(name).getBody(),HttpStatus.OK);
    }

    public ResponseEntity<Product> getProduct(int pId) {
        return new ResponseEntity<>(productFeign.findById(pId).getBody(),HttpStatus.OK);
    }


}
