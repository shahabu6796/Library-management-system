package com.library.com.Library.managament.system.service;

import com.library.com.Library.managament.system.entity.User;
import com.library.com.Library.managament.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<String> crateUser(User user) {
        // call validator call to check that all fields are present or not
        // e.g UserValidator
        // if validation is true then only send object to save inside db
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("User Account has been created successfully..");
    }

    public ResponseEntity<String> loginUser(String userEmail, String password) {
        if (userRepository.findByUserEmailAndPassword(userEmail, password) != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Login Successfull..");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Email or password");
    }
}