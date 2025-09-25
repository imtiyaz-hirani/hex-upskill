package com.upskill.supportflowlite.service;

import com.upskill.supportflowlite.model.User;
import com.upskill.supportflowlite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Integer userId) {
      Optional<User> optional =  userRepository.getUser(userId);
       if(optional.isEmpty())
           throw new RuntimeException("Invalid User Id Given");

      return optional.get();
        /*
        return userRepository.findOne(userId)
                    .orElseThrow(()->RuntimeException.class);
        */
    }
}
