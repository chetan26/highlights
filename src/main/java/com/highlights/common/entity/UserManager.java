package com.highlights.common.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    @Autowired
    UserRepository userRepository;

    public User findByUserName(String userName,String password){
        return userRepository.findByUserNameAndPassword(userName,password);
    }

    public User findByPassword(String password){
        return userRepository.findByPassword(password);
    }


}
