package com.mrz.rpc.rpc06;

import com.mrz.common.User;
import com.mrz.common.UserService;
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


        UserService bean = applicationContext.getBean(UserService.class);
        Object bean11 = applicationContext.getBeansOfType(clazzName.getClass());

//        Object bean1 = applicationContext.getBean(clazzName);
        Class<?> clazz = Class.forName(clazzName);
        //fixme 接口未注入
//        Class clazz = (Class) applicationContext.getBean(clazzName);
        Class clazz1= (Class) applicationContext.getBean(clazz);

        Method method =        clazz1.getMethod(methodName, parameterTypes);
        User user = (User) method.invoke(clazz, args);

        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(user);
        dos.flush();
    }
}
