package com.mrz.rpc.rpc07;

import com.mrz.common.entity.User;

public class ProductServiceImpl {
    public User findProductById(int id) {
        return new User(id,"测试用户");
    }
}
