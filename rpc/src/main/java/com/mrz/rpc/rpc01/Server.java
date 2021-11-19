package com.mrz.rpc.rpc01;

import com.mrz.common.entity.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static boolean running = true;

    public static void main(String[] args) throws Exception{
        //8990端口进行监听
        ServerSocket serverSocket = new ServerSocket(8990);
        while (running){
            Socket socket = serverSocket.accept();
            process(socket);
            socket.close();
        }
        serverSocket.close();
    }

    /**
     * 对接收的客户端链接进行处理
     * @param socket
     * @throws Exception
     */
    private static void process(Socket socket) throws Exception{
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        DataInputStream dis = new DataInputStream(in);
        DataOutputStream dos = new DataOutputStream(out);

        int id = dis.readInt();
        UserServiceImpl service = new UserServiceImpl();
        User user = service.findById(id);
        //将对象属性根据类型单独写回给客户端
        dos.writeInt(user.getId());
        dos.writeUTF(user.getName());
        dos.flush();
    }
}
