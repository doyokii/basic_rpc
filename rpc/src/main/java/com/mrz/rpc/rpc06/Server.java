package com.mrz.rpc.rpc06;

import com.mrz.common.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static boolean running = true;

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(8990);
        while (running) {
            Socket socket = serverSocket.accept();
            process(socket);
            socket.close();
        }
        serverSocket.close();
    }

    private static void process(Socket socket) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-config.xml");
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        ObjectInputStream ois = new ObjectInputStream(in);
        DataOutputStream dos = new DataOutputStream(out);

        String clazzName = ois.readUTF();
        String methodName = ois.readUTF();
        Class[] parameterTypes = (Class[]) ois.readObject();
        Object[] args = (Object[]) ois.readObject();

        //通过接口Class对象 并在容器中拿到接口实现的实例
        Class<?> clazz = Class.forName(clazzName);
        Object bean = applicationContext.getBean(clazz);

        Method method = bean.getClass().getMethod(methodName, parameterTypes);
        User user = (User) method.invoke(bean, args);

        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(user);
        dos.flush();
    }
}
