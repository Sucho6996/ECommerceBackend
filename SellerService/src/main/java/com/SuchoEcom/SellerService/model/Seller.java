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

/*
Model Json
{
  "username": "Rit123",
  "sellername": "Suchorit",
  "password": "suchorit",
  "earning": 0.0,
  "address": "Abc lane",
  "phoneNo": "9230128898",
  "Role": "Seller"
}
*/
