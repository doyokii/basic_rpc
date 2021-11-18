package com.mrz.rpc.rpc07;

import com.mrz.common.UserService;

public class Client {
    public static void main(String[] args) throws Exception{
        UserService service = (UserService) Stub.getStub(UserService.class);
        System.out.println(service.findById(123));
    }
}
