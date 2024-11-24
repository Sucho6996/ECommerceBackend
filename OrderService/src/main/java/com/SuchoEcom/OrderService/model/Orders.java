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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int productId;
    private String sellerName;
    private String phoneNo;
    private BigDecimal productPrice;
    private String orderStatus;
    @Lob
    private byte[] image;
    private String razorpayOrderId;
}
