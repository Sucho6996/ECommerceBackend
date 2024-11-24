package com.SuchoEcom.CustomerService.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.Name;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Users {
    @Id
    private String phoneNo;
    private String username;
    private String password;
    private String name;
    private String address;
    private String role;
}
/*
Model json
{
  "username": "string",
  "phoneNo": "string",
  "password": "string",
  "name": "string",
  "address": "string",
  "role": "string"
}
*/
