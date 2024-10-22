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
import java.util.List;

@Service
public class SellerService {

    @Autowired
    ProductFeign productFeign;
    @Autowired
    SellerRepo sellerRepo;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    public ResponseEntity<String> addSeller(Seller seller) {
      seller.setPassword(encoder.encode(seller.getPassword()));
      sellerRepo.save(seller);
      return new ResponseEntity<>("Account Created",HttpStatus.CREATED);
    }
    public ResponseEntity<String> addProduct(ProductDetails productDetails, MultipartFile imageFile) {
                productDetails.setImageName(imageFile.getName());
                productDetails.setImageType(imageFile.getContentType());
                try {
                    productDetails.setImage(imageFile.getBytes());
                } catch (IOException e) {
                    return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
                }
                String m=productFeign.addProduct(productDetails);
                return new ResponseEntity<>(m, HttpStatus.CREATED);
    }
    public ResponseEntity<String> UpdateProduct(Product product, MultipartFile imageFile) {
        List<Seller> sellers=sellerRepo.findAll();
        for (Seller seller:sellers){
            if(seller.getSellername().equals(product.getSellerName())) {
                product.setImageName(imageFile.getName());
                product.setImageType(imageFile.getContentType());
                try {
                    product.setImage(imageFile.getBytes());
                } catch (IOException e) {
                    return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
                }
                String m=productFeign.modifyProduct(product);
                return new ResponseEntity<>(m, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>("Seller isn't registered",HttpStatus.NOT_ACCEPTABLE);
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

    public ResponseEntity<String> deleteProduct(int id) {
        return productFeign.deleteById(id);
    }

    public ResponseEntity<Seller> getSeller(String name) {
        return new ResponseEntity<>(sellerRepo.findBysellername(name),HttpStatus.OK);
    }

    public ResponseEntity<String> update(Seller seller) {
        Seller seller1=sellerRepo.findBysellername(seller.getSellername());
        //seller1.setEarning(seller.getEarning());
        sellerRepo.save(seller1);
        return new ResponseEntity<>("updated!!",HttpStatus.OK);
    }


    public ResponseEntity<String> internalUpdate(String name, BigDecimal amount) {
        Seller seller=sellerRepo.findBysellername(name);
        seller.setEarning(seller.getEarning().add(amount));
        sellerRepo.save(seller);
        return new ResponseEntity<>("Amount updated",HttpStatus.OK);
    }
}


