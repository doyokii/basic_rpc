package com.mrz.rpc.rpc10;

import com.mrz.common.entity.Product;
import com.mrz.common.service.ProductService;

public class Client {
    public static void main(String[] args) throws Exception{
        ProductService service = (ProductService) Stub.getStub(ProductService.class);
        System.out.println((Product)service.findProductById(321));
    }
}
