package com.mrz.common.service;

import com.mrz.common.entity.User;
import org.springframework.stereotype.Service;

@Service()
public interface UserService {
    public User findById(int id);
}
