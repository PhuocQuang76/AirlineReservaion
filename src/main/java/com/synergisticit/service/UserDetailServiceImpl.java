package com.synergisticit.service;


import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Username:" + username);
        User user = userService.findUserByUserName(username);

        System.out.println("Userr from userService: " + user.getUsername());

        Collection<GrantedAuthority> authorities = new HashSet<>();
        if (user != null) {
            System.out.println("user from DB:" + user.getUsername());
            for(Role role : user.getRoles()){
                System.out.println(role.getRoleName());
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
        }


        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
}
