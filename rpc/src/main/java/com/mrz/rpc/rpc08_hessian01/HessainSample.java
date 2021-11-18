package com.mrz.rpc.rpc08_hessian01;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.mrz.common.entity.User;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class HessainSample {
    public static void main(String[] args) throws Exception{
        User u1 = new User(1, "zhang");
        byte[] bytes = serilize(u1);
        System.out.println(bytes.length);
        User u2 = (User) deserialize(bytes);
        System.out.println(u2);

    }

    private static byte[] serilize(Object o) throws Exception{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(baos);
        output.writeObject(o);
        output.flush();
        byte[] bytes = baos.toByteArray();
        baos.close();
        output.close();
        return bytes;
    }
    public static Object deserialize(byte[] bytes) throws Exception{
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Hessian2Input input = new Hessian2Input(bais);
        Object o = input.readObject();
        bais.close();
        input.close();
        return o;
    }
}
