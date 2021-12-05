package com.highlights.login;

import com.highlights.user.User;
import com.highlights.user.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserManager userManager;

    public ResponseEntity<String> loginUser(LoginInput loginInput) {
        try{
            User user = userManager.findByUserNameAndPassword(loginInput.getUsername(),loginInput.getPassword());
            if(user!=null)
                return ResponseEntity.ok("{\"status\": \"success\"}");
            return ResponseEntity.ok("{ \"status\":\"error\",\"message\":\"Invalid credentials\"}");
        }
        catch (Exception e){
            return ResponseEntity.ok("{ \"status\":\"error\",\"message\":\"Invalid credentials\"}");
        }
    }
}

