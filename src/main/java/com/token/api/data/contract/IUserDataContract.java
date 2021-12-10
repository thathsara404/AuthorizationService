package com.token.api.data.contract;

import com.token.api.data.model.UserModel;

public interface IUserDataContract {

    public UserModel addUser(String system, String password, String username);

    public UserModel findByUserName(String username);

    public UserModel findByUserNameAndPassword(String username, String password);

}
