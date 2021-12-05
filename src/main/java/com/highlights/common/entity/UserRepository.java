package com.highlights.common.entity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository< User, String > {

    User findByPassword(String password);
    User findByUserNameAndPassword(String userName, String password);
}
