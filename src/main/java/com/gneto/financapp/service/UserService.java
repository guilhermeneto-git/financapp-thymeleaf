package com.gneto.financapp.service;

import com.gneto.financapp.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(String id);

    User save(User user);

    void deleteById(String id);

}
