package com.mrz.rpc.rpc09_Hessian02;

import com.caucho.hessian.io.Hessian2Output;
import com.mrz.common.entity.User;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class HessianVsJDK {
    public static void main(String[] args) throws Exception {
        User u = new User(1, "zhangsan");
        System.out.println(hessianSerialize(u).length);
        System.out.println(jdkSerialize(u).length);
    }

    public static byte[] hessianSerialize(Object o) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(baos);
        output.writeObject(o);
        output.flush();
        byte[] bytes = baos.toByteArray();
        baos.close();
        output.close();
        return bytes;
    }

    public static byte[] jdkSerialize(Object o) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.flush();
        byte[] bytes = baos.toByteArray();
        baos.close();
        oos.close();
        return bytes;
    }
}
