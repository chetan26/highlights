package com.highlights.login;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public ResponseEntity<String> loginUser(LoginInput loginInput) {
        try{
            if(loginInput.getUsername().contentEquals("user1") && loginInput.getPassword().contentEquals("password1"))
                return ResponseEntity.ok("{\"status\": \"success\"}");
            return ResponseEntity.ok("{ \"status\":\"error\",\"message\":\"Invalid credentials\"}");
        }
        catch (Exception e){
            return ResponseEntity.ok("{ \"status\":\"error\",\"message\":\"Invalid credentials\"}");
        }
    }
}

