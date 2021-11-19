package com.mrz.rpc.rpc01;

import com.mrz.common.entity.User;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("127.0.0.1", 8990);

        //创建字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(123);
        //写出
        socket.getOutputStream().write(baos.toByteArray());
        socket.getOutputStream().flush();

        //读入
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        int id = dis.readInt();
        String name = dis.readUTF();
        User user = new User(id, name);

        System.out.println(user);
        dos.close();
        socket.close();
    }
}
