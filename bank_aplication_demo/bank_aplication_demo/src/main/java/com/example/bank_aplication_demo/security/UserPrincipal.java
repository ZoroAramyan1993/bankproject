package com.example.bank_aplication_demo.security;

import com.example.bank_aplication_demo.entity.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
    private Long id;
    private String name;
    private String surName;
    private String userName;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority>authorities;

    public UserPrincipal(Long id,String name,String surName,String userName,String email,String password,Collection<? extends GrantedAuthority>authorities){
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal createClient(Client client){
        List<GrantedAuthority> authorities = client.getRoles().stream().
                map(role->new SimpleGrantedAuthority(role.getRoleName().
                        name())).collect(Collectors.toList());
        return new UserPrincipal(client.getId(),
                client.getName(),
                client.getSurName(),
                client.getUserName(),
                client.getEmail(),
                client.getPassword(),
                authorities);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this==o)return true;
        if (o==null|| this.getClass()!=o.getClass())return false;
        UserPrincipal userPrincipal = (UserPrincipal)o;
        return Objects.equals(id,userPrincipal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
