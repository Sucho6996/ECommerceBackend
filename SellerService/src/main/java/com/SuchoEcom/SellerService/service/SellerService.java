package com.SuchoEcom.SellerService.service;

import com.SuchoEcom.SellerService.controller.ProductFeign;
import com.SuchoEcom.SellerService.model.Product;
import com.SuchoEcom.SellerService.model.ProductDetails;
import com.SuchoEcom.SellerService.model.Seller;
import com.SuchoEcom.SellerService.repo.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SellerService {

    @Autowired
    ProductFeign productFeign;
    @Autowired
    SellerRepo sellerRepo;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    public ResponseEntity<Map<String,String>> addSeller(Seller seller) {
        Map<String,String> response=new HashMap<>();
        seller.setPassword(encoder.encode(seller.getPassword()));
        sellerRepo.save(seller);
        response.put("message","Account Created");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    public ResponseEntity<Map<String,String>> addProduct(ProductDetails productDetails, MultipartFile imageFile) {
        Map<String,String> response=new HashMap<>();
        productDetails.setImageName(imageFile.getName());
        productDetails.setImageType(imageFile.getContentType());
        try {
            productDetails.setImage(imageFile.getBytes());
        } catch (IOException e) {
            response.put("message",e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        String m=productFeign.addProduct(productDetails);
        response.put("message",m);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    public ResponseEntity<Map<String,String>> UpdateProduct(Product product, MultipartFile imageFile) {
        Map<String,String> response=new HashMap<>();
        List<Seller> sellers=sellerRepo.findAll();
        for (Seller seller:sellers){
            if(seller.getSellername().equals(product.getSellerName())) {
                product.setImageName(imageFile.getName());
                product.setImageType(imageFile.getContentType());
                try {
                    product.setImage(imageFile.getBytes());
                } catch (IOException e) {
                    response.put("message",e.getMessage());
                    return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
                }
                String m=productFeign.modifyProduct(product);
                response.put("message",m);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        }
        response.put("message","Seller isn't registered");
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Product>> sellerView(String sellerName) {
        List<Product> productDetails=new ArrayList<>();
        List<Product> productDetailsList=new ArrayList<>();
        productDetailsList=productFeign.findAll().getBody();
        for (Product p:productDetailsList){
            if(p.getSellerName().equals(sellerName))
                productDetails.add(p);
        }
        return new ResponseEntity<>(productDetails,HttpStatus.FOUND);
    }

    public ResponseEntity<Map<String,String>> deleteProduct(int id) {
        Map<String,String> response=new HashMap<>();
        response.put("message",productFeign.deleteById(id).getBody());
        return new ResponseEntity<>(response,HttpStatus.OK) ;
    }

    public ResponseEntity<Seller> getSeller(String name) {
        return new ResponseEntity<>(sellerRepo.findBysellername(name),HttpStatus.OK);
    }

    public ResponseEntity<Map<String,String>>  update(Seller seller) {
        Map<String,String> response=new HashMap<>();
        Seller seller1=sellerRepo.findBysellername(seller.getSellername());
        //seller1.setEarning(seller.getEarning());
        sellerRepo.save(seller1);
        response.put("message","updated!!");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


    public ResponseEntity<Map<String,String>> internalUpdate(String name, BigDecimal amount) {
        Map<String,String> response=new HashMap<>();
        Seller seller=sellerRepo.findBysellername(name);
        seller.setEarning(seller.getEarning().add(amount));
        sellerRepo.save(seller);
        response.put("message","Amount updated");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}


