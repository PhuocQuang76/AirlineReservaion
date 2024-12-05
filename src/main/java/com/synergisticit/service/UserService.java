package com.synergisticit.service;

import com.synergisticit.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public User save(User user);
    public List<User> findAll();

    public User findById(Long userId);

    public User updateById(Long userId);

    public void deleteById(Long userId);

    public User findUserByUserName(String userName);

    public User update(User user);
}
