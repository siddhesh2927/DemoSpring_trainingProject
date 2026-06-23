package com.example.demo.Controller;

import com.example.demo.Entity.Product;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping
    public Product addProuct(@RequestBody @Valid Product p){
        return service.addProduct(p);
    }

    @PostMapping("/list")
    public List<Product> saveProductsByList(@RequestBody @Valid List<Product> products){
        return service.saveProductList(products);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return service.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product UpdateProduct(@PathVariable Long id , @RequestBody @Valid Product product){
        return service.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable Long id){
        return service.deleteProductById(id);
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String category){
        return service.getProductByCategory(category);
    }

}