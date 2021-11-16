package com.mrz.rpc.rpc04;

import com.mrz.common.User;
import com.mrz.common.UserService;

import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static boolean running = true;

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8990);
        while (running){
            Socket socket = serverSocket.accept();
            process(socket);
            socket.close();
        }
        serverSocket.close();
    }

    private static void process(Socket socket) throws Exception{
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        ObjectInputStream ois = new ObjectInputStream(in);
        DataOutputStream dos = new DataOutputStream(out);

        String methodName = ois.readUTF();
        Class[] parameterTypes = (Class[]) ois.readObject();
        Object[] args = (Object[]) ois.readObject();

        UserServiceImpl userService = new UserServiceImpl();
        Method method = userService.getClass().getMethod(methodName, parameterTypes);
        User user = (User) method.invoke(userService, args);

        dos.writeInt(user.getId());
        dos.writeUTF(user.getName());
        dos.flush();
    }
}
