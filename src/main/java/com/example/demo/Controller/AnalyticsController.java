package com.example.demo.Controller;

import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private OrderRepository orderRepo;

    @GetMapping
    public Map<String, Object> getAnalytics() {
        Map<String, Object> analytics = new HashMap<>();
        
        long totalCustomers = customerRepo.count();
        long totalProducts = productRepo.count();
        long totalOrders = orderRepo.count();
        
        Double revenue = orderRepo.totalRevenue();
        if (revenue == null) {
            revenue = 0.0;
        }

        analytics.put("customers", totalCustomers);
        analytics.put("products", totalProducts);
        analytics.put("orders", totalOrders);
        analytics.put("revenue", revenue);

        return analytics;
    }
}
