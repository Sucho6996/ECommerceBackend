package com.SuchoEcom.CustomerService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Cart {
    private Integer cartId;
    private int productId;
    private String sellerName;
    private String phoneNo;
    private BigDecimal productPrice;
    @Lob
    private byte[] image;
}
