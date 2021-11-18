package com.mrz.rpc.rpc07;

import com.mrz.common.entity.User;
import com.mrz.common.service.UserService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Stub {

    public static Object getStub(Class clazz) {
        InvocationHandler h = (proxy, method, args) -> {
            Socket socket = new Socket("127.0.0.1", 8990);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            String clazzName = clazz.getName();
            String methodName = method.getName();
            Class[] parametersTypes = method.getParameterTypes();

            oos.writeUTF(clazzName);
            oos.writeUTF(methodName);
            oos.writeObject(parametersTypes);
            oos.writeObject(args);
            oos.flush();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            User user = (User) ois.readObject();

            oos.close();
            socket.close();
            return user;
        };
        //fixme 待抽象？
        Object o = Proxy.newProxyInstance(UserService.class.getClassLoader(), new Class[]{UserService.class}, h);
        return o;
    }
}
