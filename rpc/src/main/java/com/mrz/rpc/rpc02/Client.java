package com.mrz.rpc.rpc02;

import com.mrz.common.User;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{
        Stub stub = new Stub();
        System.out.println(stub.findById(123));
    }
}
