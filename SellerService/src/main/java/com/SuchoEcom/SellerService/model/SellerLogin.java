package com.SuchoEcom.SellerService.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class SellerLogin {
    private String username;
    private String password;

}

/*
Model Json
{
  "username": "string",
  "password": "string"
}
*/
