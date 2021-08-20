package dev.nurlan.service;

import dev.nurlan.entity.Customer;
import dev.nurlan.response.RespCustomer;
import dev.nurlan.response.RespCustomerList;
import dev.nurlan.response.RespStatus;

public interface CustomerService {

    RespCustomerList getCustomerList();

    RespCustomer getCustomerById(Long customerId);

}
