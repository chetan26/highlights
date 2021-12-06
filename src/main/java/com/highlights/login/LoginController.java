package com.highlights.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(value = "/login")
    public UserDetails loginUser(@RequestBody LoginInput loginInput)  {
        return loginService.loginUser(loginInput);
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<String> logout(HttpServletRequest request)  {
        request.getSession().invalidate();

        return ResponseEntity.ok("{\"status\": \"Success\"}");
    }
}
