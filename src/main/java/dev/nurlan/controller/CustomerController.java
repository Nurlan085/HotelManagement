package dev.nurlan.controller;


import dev.nurlan.request.ReqCustomer;
import dev.nurlan.response.RespCustomer;
import dev.nurlan.response.RespCustomerList;
import dev.nurlan.response.RespStatus;
import dev.nurlan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/updateCustomer")
    public RespStatus updateCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.updateCustomer(reqCustomer);
    }

    @PostMapping(value = "/createCustomer")
    public RespStatus createCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.createCustomer(reqCustomer);
    }

    @PostMapping(value = "/deleteCustomer/{customerId}")
    public RespStatus deleteCustomer(@PathVariable("customerId") Long customerId) {
        return customerService.deleteCustomer(customerId);
    }

}
