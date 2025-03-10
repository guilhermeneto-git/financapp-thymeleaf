package com.gneto.financapp.service;

import com.gneto.financapp.dao.UserRepository;
import com.gneto.financapp.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }

        throw new EntityNotFoundException("User not found by id - " + id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(String id) {
        Optional<User> result = userRepository.findById(id);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("User not deleted by id - " + id);
        }

        userRepository.deleteById(id);
    }

}
