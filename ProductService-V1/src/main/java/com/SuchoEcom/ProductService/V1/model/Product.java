package com.SuchoEcom.ProductService.V1.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private String productDesc;
    private String productBrand;
    private BigDecimal productPrice;
    private String productCat;//->Category
    private String productReleaseDate;
    private int productQuantity;
    private Boolean productAvailable;
    private String sellerName;

    //For Image
    private String imageName;
    private String imageType;
    @Lob
    private byte[] image;

}
