package com.token.api.data.repository;

import com.token.api.data.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<UserModel, String> {

    public UserModel findByUsername(String username);

    public UserModel findByUsernameAndPassword(String username,String password);

}
