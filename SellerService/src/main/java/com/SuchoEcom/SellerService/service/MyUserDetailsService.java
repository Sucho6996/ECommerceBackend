package com.SuchoEcom.SellerService.service;


import com.SuchoEcom.SellerService.model.Seller;
import com.SuchoEcom.SellerService.model.UserPrinciple;
import com.SuchoEcom.SellerService.repo.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    SellerRepo repo;
    @Override
    public UserDetails loadUserByUsername(String sellername) throws UsernameNotFoundException {
        Seller seller=repo.findBysellername(sellername);
        if (seller==null){
            System.out.println("404 not found");
            throw new UsernameNotFoundException("Username 404");
        }
        return new UserPrinciple(seller);
    }
}
