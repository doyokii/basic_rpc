package com.mrz.rpc.rpc06;

import com.mrz.common.entity.User;

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
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, h);
        return o;
    }
}
