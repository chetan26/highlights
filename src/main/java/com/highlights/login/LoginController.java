package com.highlights.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(value = "/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginInput loginInput)  {
        return loginService.loginUser(loginInput);
    }
}
