package com.synergisticit.service;

import com.synergisticit.domain.User;
import com.synergisticit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public User save(User user) {
        String encryptedPassword = encoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();

    }

    @Override
    public User findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    @Override
    public User updateById(Long userId) {
        Optional<User> user = null;
        user =  userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findUserByUsername(userName);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }
}
