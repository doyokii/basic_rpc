package com.mrz.rpc.rpc10;

import com.mrz.common.entity.Product;
import com.mrz.common.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product findProductById(int id) {
        return new Product(id,"测试产品");
    }
}
