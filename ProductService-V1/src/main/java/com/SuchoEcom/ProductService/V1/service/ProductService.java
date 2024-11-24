package com.SuchoEcom.ProductService.V1.service;



import com.SuchoEcom.ProductService.V1.model.Product;
import com.SuchoEcom.ProductService.V1.model.ProductDetails;
import com.SuchoEcom.ProductService.V1.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;
    public ResponseEntity<List<Product>> findAll(String sellerName) {
        List<Product> products=productRepo.findAllBysellerName(sellerName);
        if(!products.isEmpty()){
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Product> findById(Integer id) {
        Product product=productRepo.findById(id).orElse(new Product());
        if (product!=null){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<Product>> findByKeyword(String keyword) {
        List<Product> products=productRepo.findByKeyword(keyword);
        if(!products.isEmpty())
            return new ResponseEntity<>(products,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public String save(ProductDetails productDetails)  {

        Product p=new Product();

        p.setProductName(productDetails.getProductName());
        p.setProductDesc(productDetails.getProductDesc());
        p.setProductBrand(productDetails.getProductBrand());
        p.setProductPrice(productDetails.getProductPrice());
        p.setProductCat(productDetails.getProductCat());
        p.setProductReleaseDate(productDetails.getProductReleaseDate());
        p.setProductQuantity(productDetails.getProductQuantity());
        p.setProductAvailable(productDetails.getProductAvailable());
        p.setSellerName(productDetails.getSellerName());
        p.setImageName(productDetails.getImageName());
        p.setImageType(productDetails.getImageType());
        p.setImage(productDetails.getImage());

        productRepo.save(p);
        return "Product Added Successfully";
    }
    public String update(Product product){

        Product p=productRepo.findById(product.getProductId()).get();

        //if(product.getSellerName()==p.getSellerName()){
            p.setProductId(product.getProductId());
            p.setProductName(product.getProductName());
            p.setProductDesc(product.getProductDesc());
            p.setProductBrand(product.getProductBrand());
            p.setProductPrice(product.getProductPrice());
            p.setProductCat(p.getProductCat());
            p.setProductReleaseDate(product.getProductReleaseDate());
            p.setProductQuantity(product.getProductQuantity());
            p.setProductAvailable(product.getProductAvailable());
            p.setSellerName(product.getSellerName());
            p.setImageName(product.getImageName());
            p.setImageType(product.getImageType());
            p.setImage(product.getImage());

            productRepo.save(p);
            return "Product Updated Successfully";
//        }
//        return "Something went Wrong!!";
    }

    public ResponseEntity<String> deleteById(Integer id) {
        productRepo.deleteById(id);
        if (productRepo.findById(id).isEmpty())
            return new ResponseEntity<>("Deleted successfully...",HttpStatus.OK);
        else
            return new ResponseEntity<>("This guys is still alive!!",HttpStatus.BAD_REQUEST);
    }
}
