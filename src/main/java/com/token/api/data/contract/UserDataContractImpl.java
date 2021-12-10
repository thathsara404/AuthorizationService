package com.token.api.data.contract;

import com.token.api.data.model.UserModel;
import com.token.api.data.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDataContractImpl implements IUserDataContract {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserModel addUser(String system, String password, String username) {

        UserModel userModel = new UserModel();
        userModel.setSystem(system);
        userModel.setPassword(password);
        userModel.setUsername(username);
        userRepository.save(userModel);

        return userModel;
    }

    @Override
    public UserModel findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserModel findByUserNameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

}
