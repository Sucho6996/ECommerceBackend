package com.SuchoEcom.SellerService.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seller {
    @Id
    private String username;
    private String sellername;
    private String password;
    private BigDecimal earning;
    private String address;
    private String phoneNo;
    private String Role;
}
