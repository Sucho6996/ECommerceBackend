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
public class Orders {
    private Integer id;
    private int productId;
    private String sellerName;
    private String phoneNo;
    private BigDecimal productPrice;
    final private String PaymentMethod="COD";
    @Lob
    private byte[] image;
}
