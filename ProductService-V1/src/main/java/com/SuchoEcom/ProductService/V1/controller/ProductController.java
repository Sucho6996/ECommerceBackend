package com.SuchoEcom.ProductService.V1.controller;

import com.SuchoEcom.ProductService.V1.model.Product;
import com.SuchoEcom.ProductService.V1.model.ProductDetails;
import com.SuchoEcom.ProductService.V1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Product>> findAll(){
        return productService.findAll();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id){
        return productService.findById(id);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Product>> findByKeyword(@PathVariable String keyword){
        //System.out.println("Search with "+keyword);//->Use this when have UI It will be fun to look
        return productService.findByKeyword(keyword);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImg(@PathVariable Integer id){
        Product product=productService.findById(id).getBody();
        if (product.getProductId()>0 & product.getImage()!=null)
            return new ResponseEntity<>(product.getImage(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody ProductDetails productDetails){
        return productService.save(productDetails);
    }
    @PutMapping("/modify")
    public String modifyProduct(@RequestBody Product product){
        return productService.update(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id){
        return productService.deleteById(id);
    }

}
