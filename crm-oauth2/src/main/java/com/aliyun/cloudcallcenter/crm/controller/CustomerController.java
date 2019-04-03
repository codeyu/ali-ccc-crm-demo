package com.aliyun.cloudcallcenter.crm.controller;

import java.util.List;

import com.aliyun.cloudcallcenter.crm.model.Customer;
import com.aliyun.cloudcallcenter.crm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author edward
 * @date 2017/11/12
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/customers")
    public String listAll(Model model) {
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @RequestMapping("/customer/phoneNumber/{phoneNumber}")
    public String findByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber, Model model) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber);
        model.addAttribute("customer", customer);
        return "customer";
    }

    @RequestMapping("/customer/userName/{userName}")
    public String findByUserName(@PathVariable("userName") String userName, Model model) {
        Customer customer = customerRepository.findByUserName(userName);
        model.addAttribute("customer", customer);
        return "customer";
    }
}
