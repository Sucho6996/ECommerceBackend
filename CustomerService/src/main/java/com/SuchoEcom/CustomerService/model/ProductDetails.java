package com.SuchoEcom.CustomerService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProductDetails {
    private int productId;
    private String sellerName;
    private String phoneNo;
    private BigDecimal productPrice;
}
