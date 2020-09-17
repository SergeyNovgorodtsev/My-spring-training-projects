package com.luv2code.springdemo.controller;

import java.util.List;

import com.luv2code.springdemo.dao.CustomerDAOImpl;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel) {

        List<Customer> customers = customerService.getCustomers();

        theModel.addAttribute("customers", customers);

        return "list-customers";
    }
    @RequestMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {


        //create model attribute to bind form data
        Customer customer = new Customer();

        model.addAttribute("customer", customer);

        return "customer-form";
    }
    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        //save the customer using our service
        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId")
                                    int theId,
                                    Model model) {
        //get the customer from the database
        Customer customer = customerService.getCustomer(theId);
        //set customer as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);
        //send over to our form
        return "customer-form";
    }
    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) {
        customerService.deleteCustomer(theId);
        return "redirect:/customer/list";
    }
    @GetMapping("/search")
    public String search(@RequestParam("theSearchName") String name,
                         Model model) {
        List<Customer> customers = customerService.searchCustomers(name);
        model.addAttribute("customers", customers);
        return "list-customers";
    }
    @GetMapping("/showFullList")
    public String showFullList(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "list-customers";
    }

}


