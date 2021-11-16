package com.mrz.rpc.rpc06;

import com.mrz.common.User;
import com.mrz.common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findById(int id) {
        return new User(id,"测试用户");
    }
}
