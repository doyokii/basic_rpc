package com.mrz.rpc.rpc02;

import com.mrz.common.entity.User;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
/**
 * @author : Zhuang Jialong
 * @description : 代理类 封装网络层io操作 屏蔽细节
 * @date :
 * @Copyright:
 */
public class Stub {
    public User findById(int id) throws Exception{
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
    }
}
