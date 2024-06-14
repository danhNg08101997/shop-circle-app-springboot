package com.project.shopapp.service.imp;

import com.project.shopapp.dto.UserDTO;
import com.project.shopapp.exception.DataNotFoundException;
import com.project.shopapp.model.UserModel;

public interface UserServiceImp {
    UserModel addUser(UserDTO userDTO) throws DataNotFoundException;
    String login(String phoneNumber, String password);
}
