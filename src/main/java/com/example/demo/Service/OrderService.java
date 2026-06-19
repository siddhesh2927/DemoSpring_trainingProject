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

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private CustomerRepository custRepo;

    @Autowired
    private ProductRepository productRepo;


    public Orders placeOrder(Long custId , Long ProdId , int qty){
        Customer customer=custRepo.findById(custId).orElse(null);

        if(customer!=null){
            Product product=productRepo.findById(ProdId).orElse(null);

            if(product!=null){
                Orders order = new Orders();
                order.setCustomer(customer);
                order.setProduct(product);
                order.setQuantityOrdered(qty);

                order.setTotalPrice(product.getPrice()*qty);

                return orderRepo.save(order);
            }
        }
        return null;
    }


    public List<Orders> getAllOrders(){
        return  orderRepo.findAll();
    }




}