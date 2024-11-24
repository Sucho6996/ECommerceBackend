package com.SuchoEcom.OrderService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer cartId;
    private int productId;
    private String sellerName;
    private String phoneNo;
    private BigDecimal productPrice;
    @Lob
    private byte[] image;
}
