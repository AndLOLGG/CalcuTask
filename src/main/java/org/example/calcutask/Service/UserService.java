package org.example.calcutask.Service;

import org.example.calcutask.Model.User;
import org.example.calcutask.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findById(int id) {
        return userRepository.findById(id);
    }

    public User authenticateAndGetUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPasswordHash().equals(password)) {
            return user;
        }
        return null;
    }

}

