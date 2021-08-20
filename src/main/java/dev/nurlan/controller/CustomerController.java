package dev.nurlan.controller;


import dev.nurlan.entity.Customer;
import dev.nurlan.response.RespCustomer;
import dev.nurlan.response.RespCustomerList;
import dev.nurlan.response.RespStatus;
import dev.nurlan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping(value = "/getCustomerList", method = {RequestMethod.GET, RequestMethod.POST})
    public RespCustomerList getCustomerList() {
        return customerService.getCustomerList();
    }

    @RequestMapping(value = "/getCustomerById/{customerId}", method = {RequestMethod.GET, RequestMethod.POST})
    public RespCustomer getCustomerById(@PathVariable("customerId") Long customerId) {
        return customerService.getCustomerById(customerId);
    }

}
