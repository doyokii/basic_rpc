package com.mrz.rpc.rpc06;

import com.mrz.common.entity.User;
import com.mrz.common.service.UserService;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findById(int id) {
        return new User(id,"测试用户");
    }
}
