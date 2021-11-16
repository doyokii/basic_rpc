package com.mrz.rpc.rpc04;

import com.mrz.common.User;
import com.mrz.common.UserService;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Stub {

    public static UserService getStub() {
        InvocationHandler h = (proxy, method, args) -> {
            Socket socket = new Socket("127.0.0.1", 8990);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            String methodName = method.getName();
            Class[] parametersTypes = method.getParameterTypes();
            oos.writeUTF(methodName);
            oos.writeObject(parametersTypes);
            oos.writeObject(args);
            oos.flush();

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            int receiveId = dis.readInt();
            String name = dis.readUTF();
            User user = new User(receiveId, name);

            oos.close();
            socket.close();
            return user;
        };
        Object o = Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class[]{UserService.class}, h);
        return (UserService) o;
    }
}
