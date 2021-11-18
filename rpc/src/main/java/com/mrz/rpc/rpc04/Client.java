package com.mrz.rpc.rpc04;

import com.mrz.common.service.UserService;

public class Client {
    public static void main(String[] args) throws Exception{
        UserService service = Stub.getStub();
        System.out.println(service.findById(123));
    }
}
