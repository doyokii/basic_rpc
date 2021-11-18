package com.mrz.rpc.rpc04;

import com.mrz.common.entity.User;
import com.mrz.common.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User findById(int id) {
        return new User(id,"测试用户");
    }
}
