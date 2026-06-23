package com.example.demo.Service;

import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Orders;
import com.example.demo.Entity.Product;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.demo.Exception.ResourceNotFoundException;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CustomerRepository custRepo;

    @Autowired
    private ProductRepository productRepo;


    public Orders placeOrder(Long custId , Long ProdId , int qty){
        Customer customer = custRepo.findById(custId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + custId));

        Product product = productRepo.findById(ProdId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + ProdId));

        if (product.getQuantity() < qty) {
            throw new IllegalArgumentException("Insufficient stock for product: " + product.getProductName());
        }

        // Deduct inventory
        product.setQuantity(product.getQuantity() - qty);
        productRepo.save(product);

        Orders order = new Orders();
        order.setCustomer(customer);
        order.setProduct(product);
        order.setQuantityOrdered(qty);
        order.setTotalPrice(product.getPrice() * qty);

        return orderRepo.save(order);
    }


    public List<Orders> getAllOrders(){
        return  orderRepo.findAll();
    }




}