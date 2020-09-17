package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    CustomerService service;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return service.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {
        if (service.getCustomer(customerId) == null ) {
            throw new CustomerNotFoundException("Customer id not found -- " + customerId);
        } else {
            return service.getCustomer(customerId);
        }
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer) {

        customer.setId(0);

        service.saveCustomer(customer);

        return customer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer (@RequestBody Customer customer) {
        service.saveCustomer(customer);

        return customer;
    }

    @DeleteMapping("/customers/{customerId}")
    public String deleteCustomer (@PathVariable int customerId) {
        if (service.getCustomer(customerId) == null) {

            throw new CustomerNotFoundException("Customer id not found - " + customerId);
        }

        service.deleteCustomer(customerId);
        return "Deleted customer id - " + customerId;
    }
}
