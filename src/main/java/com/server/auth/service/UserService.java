package com.server.auth.service;

import com.server.auth.domain.user.User;
import com.server.auth.domain.user.exceptions.UserNotFoundException;
import com.server.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUserByEmail(String email) throws UserNotFoundException{
        Optional<User> userOptional = userRepository.findByEmail(email);

        return userOptional.orElseThrow(UserNotFoundException::new);
    }

    public User createUser(User user){
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);

        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
}
