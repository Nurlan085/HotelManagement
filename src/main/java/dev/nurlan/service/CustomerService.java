package dev.nurlan.service;

import dev.nurlan.request.ReqCustomer;
import dev.nurlan.response.RespCustomer;
import dev.nurlan.response.RespCustomerList;
import dev.nurlan.response.RespStatus;

public interface CustomerService {

    RespCustomerList getCustomerList();

    RespCustomer getCustomerById(Long customerId);

    RespStatus updateCustomer(ReqCustomer reqCustomer);

    RespStatus createCustomer(ReqCustomer reqCustomer);

    RespStatus deleteCustomer(Long customerId);

}
