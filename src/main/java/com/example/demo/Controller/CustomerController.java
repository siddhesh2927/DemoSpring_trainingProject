package com.example.demo.Controller;


import com.example.demo.Dto.CustomerDto;
import com.example.demo.Entity.Customer;
import com.example.demo.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService service;

    private CustomerDto convertToDto(Customer customer) {
        if (customer == null) return null;
        CustomerDto dto = new CustomerDto();
        dto.setCustomerId(customer.getCustomerId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setCity(customer.getCity());
        return dto;
    }

    @GetMapping
    public List<CustomerDto> getAllCustomer(){
        return service.getAllCustomers().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CustomerDto saveCustomer(@RequestBody @Valid Customer cust){
        return convertToDto(service.SaveCustomer(cust));
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomerbyId(@PathVariable Long id){
        return convertToDto(service.getCustomerById(id));
    }

    @GetMapping("/email/{email}")
    public CustomerDto getCustomerByEmail(@PathVariable String email){
        return convertToDto(service.getCustomerByEmail(email));
    }

    @GetMapping("/city/{city}")
    public List<CustomerDto> getCustomerByCity(@PathVariable String city){
        return service.getCustomerByCity(city).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/age/{age}")
    public List<CustomerDto> getCustomerByAge(@PathVariable int age){
        return service.getCustomerByAge(age).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/list")
    public List<CustomerDto> SaveCustomerByAList(@RequestBody @Valid List<Customer> cust){
        return service.saveCustomerList(cust).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{Id}")
    public CustomerDto UpdateCustomerById(@PathVariable Long Id , @RequestBody @Valid Customer cust){
        return convertToDto(service.UpdateCustomer(Id,cust));
    }

    @DeleteMapping("/{Id}")
    public void DeleteCustomerById(@PathVariable Long Id){
        service.deleteCustomer(Id);
    }

}