package com.example.demo.Service;

import com.example.demo.Entity.Product;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public Product addProduct(Product p){
        return repo.save(p);
    }

    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    public List<Product> saveProductList(List<Product> products){
        return repo.saveAll(products);
    }



    public Product getProductById(Long productId){
        return repo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));
    }


    public Product updateProduct(Long id,Product p){
        Product product = getProductById(id);

        product.setProductName(p.getProductName());
        product.setPrice(p.getPrice());
        product.setCategory(p.getCategory());
        product.setQuantity(p.getQuantity());

        return repo.save(product);
    }

    public String deleteProductById(Long Id){
        Product product = getProductById(Id);
        repo.delete(product);
        return "Product deleted Successfully";
    }



    public List<Product> getProductByCategory(String category){
        return repo.findByCategory(category);
    }



}