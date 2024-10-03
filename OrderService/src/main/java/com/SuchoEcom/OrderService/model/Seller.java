package com.SuchoEcom.OrderService.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    private String sellerName;
    private BigDecimal earning;
    private String address;
    private String phoneNo;
}
