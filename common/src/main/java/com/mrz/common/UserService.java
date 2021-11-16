package com.mrz.common;

import org.springframework.stereotype.Service;

@Service()
public interface UserService {
    public User findById(int id);
}
