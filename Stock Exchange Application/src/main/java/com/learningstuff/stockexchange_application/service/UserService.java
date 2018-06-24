package com.learningstuff.stockexchange_application.service;

import com.learningstuff.stockexchange_application.model.User;
import com.learningstuff.stockexchange_application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user)
    {
        userRepository.save(user);
    }

    public User findUserByEmail(String email) {

        return userRepository.getOne(email);
    }
}
