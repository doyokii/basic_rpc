package com.mrz.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor

public class Product implements Serializable {
    private int id;
    private String name;
}
