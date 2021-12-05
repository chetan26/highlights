package com.highlights.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    @Autowired
    UserRepository userRepository;

    public User findByUserNameAndPassword(String userName,String password){
        return userRepository.findByUserNameAndPassword(userName,password);
    }

}
