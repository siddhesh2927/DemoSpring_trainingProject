package com.example.demo.Dto;

import lombok.*;

@Data
public class CustomerDto {
    private Long customerId;
    private String name;
    private String email;
    private String city;
}