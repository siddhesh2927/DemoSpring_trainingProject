package com.example.demo.Dto;

import lombok.Data;

@Data
public class OrderRequest {
    private int quantityOrdered;
    private double totalPrice;
    private Long customerId;
    private Long productId;
}