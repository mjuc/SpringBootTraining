package org.demo.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.demo.entity.User;
import org.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    public User getUserById(Integer id){
        return userRepository.getById(id);
    }
    public User getUserByUsername(String username){
        return userRepository.getByUsername(username);
    }
    public boolean usernameExists(String username){
        return  userRepository.findByUsername(username).isPresent();
    }
}
