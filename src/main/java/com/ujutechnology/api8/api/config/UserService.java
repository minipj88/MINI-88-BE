package com.ujutechnology.api8.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public Optional<User> findByIdPw(String id) { return userRepository.findById(id); }
}