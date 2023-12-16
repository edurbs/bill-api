package com.edurbs.bill.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;

@Getter
public class UserSystem extends org.springframework.security.core.userdetails.User {

    private com.edurbs.bill.api.model.User user;

    public UserSystem(com.edurbs.bill.api.model.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;

    }

}
