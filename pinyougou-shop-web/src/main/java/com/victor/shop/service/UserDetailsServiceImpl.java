package com.victor.shop.service;

import com.victor.pojo.TbSeller;
import com.victor.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
    private SellerService sellerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("经过了UserDetailsServiceImpl" + username);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        TbSeller seller = sellerService.findOne(username);
        if (seller != null) {
            if (seller.getStatus().equals("1")) {
                return new User(username, seller.getPassword(), grantedAuthorityList);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }
}
