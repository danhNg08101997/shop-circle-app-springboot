package com.project.shopapp.service;

import com.project.shopapp.dto.UserDTO;
import com.project.shopapp.exception.DataNotFoundException;
import com.project.shopapp.model.RoleModel;
import com.project.shopapp.model.UserModel;
import com.project.shopapp.repository.RoleRepository;
import com.project.shopapp.repository.UserRepository;
import com.project.shopapp.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserModel addUser(UserDTO userDTO) throws DataNotFoundException {
        String phoneNumber = userDTO.getPhoneNumber();
        //Kiểm tra xem số điện thoại đã tồn tại hay chưa
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DataIntegrityViolationException("Phone number already exists");
        }
        UserModel userModel = UserModel.builder()
                .fullName(userDTO.getFullName())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(userDTO.getPassword())
                .address(userDTO.getAddress())
                .dateOfBirth(userDTO.getDateOfBirth())
                .facebookAccountId(userDTO.getFacebookAccountId())
                .googleAccountId(userDTO.getGoogleAccountId())
                .build();
        RoleModel roleModel = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(()->new DataNotFoundException("Role not found"));
        userModel.setRoleId(roleModel);
        if (userDTO.getFacebookAccountId() == 0 && userDTO.getGoogleAccountId()==0) {
            String password = userDTO.getPassword();
//            String encodePassword = passwordEndcoder.encode(password);
//            userModel.setPassword(encodePassword);
        }
        return userRepository.save(userModel);
    }

    @Override
    public String login(String phoneNumber, String password) {
        return "";
    }
}
