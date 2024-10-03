package com.SuchoEcom.SellerService.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProductDetails {
    private String productName;
    private String productDesc;
    private String productBrand;
    private BigDecimal productPrice;
    private String productCat;//->Category
    private Date productReleaseDate;
    private int productQuantity;
    private Boolean productAvailable;
    //For Image
    private String imageName;
    private String imageType;
    @Lob
    private byte[] image;
    private String sellerName;


}
