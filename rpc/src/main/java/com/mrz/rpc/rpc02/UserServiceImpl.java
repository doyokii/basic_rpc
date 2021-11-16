package com.mrz.rpc.rpc02;

import com.mrz.common.User;
import com.mrz.common.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User findById(int id) {
        return new User(id,"测试用户");
    }
}
