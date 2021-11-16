package com.mrz.rpc.rpc05;

import com.mrz.common.UserService;

public class Client {
    public static void main(String[] args) throws Exception{
        UserService service = Stub.getStub();
        System.out.println(service.findById(123));
    }
}
