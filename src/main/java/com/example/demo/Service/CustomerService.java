package com.example.demo.Service;


import com.example.demo.Entity.Customer;
import com.example.demo.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.demo.Exception.ResourceNotFoundException;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> getAllCustomers(){
        return repository.findAll();
    }

    public Customer SaveCustomer(Customer customer){
        return repository.save(customer);
    }

    public Customer getCustomerById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    public Customer getCustomerByEmail(String email){
        return repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with email: " + email));
    }

    public List<Customer> getCustomerByCity(String City){
        return repository.findByCity(City);
    }

    public List<Customer> getCustomerByAge(int age){
        return repository.findByAgeGreaterThan(age);
    }

    public List<Customer> saveCustomerList(List<Customer> cust){
        return repository.saveAll(cust);
    }

    public Customer UpdateCustomer(Long id , Customer cust){
        Customer ExistingCust = getCustomerById(id);

        ExistingCust.setName(cust.getName());
        ExistingCust.setAge(cust.getAge());
        ExistingCust.setEmail(cust.getEmail());
        if(cust.getPassword() != null) ExistingCust.setPassword(cust.getPassword());
        ExistingCust.setBankUserName(cust.getBankUserName());
        ExistingCust.setCity(cust.getCity());

        return repository.save(ExistingCust);
    }

    public void deleteCustomer(Long Id){
        repository.deleteById(Id);
    }



}