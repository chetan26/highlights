package com.highlights.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.TestingAuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;

@Service
public class LoginService {

    @Autowired
    private TestingAuthenticationProvider daoAuthenticationProvider;

    public UserDetails loginUser(LoginInput loginInput) {
        UserDetails userContext=null;
        try{

            Authentication authentication=new UsernamePasswordAuthenticationToken(loginInput.getUsername(),loginInput.getPassword());
             daoAuthenticationProvider.authenticate(authentication);

            UsernamePasswordAuthenticationToken token= (UsernamePasswordAuthenticationToken) authentication;
            SecurityContextHolder.getContext().setAuthentication(authentication);
            userContext=new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return null;
                }

                @Override
                public String getPassword() {
                    return (String) token.getCredentials();
                }

                @Override
                public String getUsername() {
                    return (String) token.getPrincipal();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return false;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return false;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return false;
                }

                @Override
                public boolean isEnabled() {
                    return false;
                }
            };

            return userContext;
        }
        catch (Exception e){
            throw new RuntimeException("Username or password not found");
        }
    }
}

