package com.mrz.rpc.rpc03;

import com.mrz.common.User;
import com.mrz.common.UserService;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Stub {

    public static UserService getStub(){
        InvocationHandler h = (proxy, method, args) -> {
            Socket socket = new Socket("127.0.0.1", 8990);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            dos.writeInt(123);

            socket.getOutputStream().write(baos.toByteArray());
            socket.getOutputStream().flush();

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            int receiveId = dis.readInt();
            String name = dis.readUTF();
            User user = new User(receiveId, name);

            dos.close();
            socket.close();
            return user;
        };
        Object o = Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class[]{UserService.class}, h);
        return (UserService) o;
    }
}
