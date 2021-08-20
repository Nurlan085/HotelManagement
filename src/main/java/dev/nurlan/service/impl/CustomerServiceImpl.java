package dev.nurlan.service.impl;

import dev.nurlan.entity.Customer;
import dev.nurlan.enums.EnumAvailableStatus;
import dev.nurlan.exception.ExceptionConstants;
import dev.nurlan.repository.CustomerDao;
import dev.nurlan.response.RespCustomer;
import dev.nurlan.response.RespCustomerList;
import dev.nurlan.response.RespStatus;
import dev.nurlan.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;


    @Override
    public RespCustomerList getCustomerList() {

        RespCustomerList response = new RespCustomerList();

        try {
            List<RespCustomer> respCustomerList = new ArrayList<>();

            List<Customer> customerList = customerDao.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
            if (customerList.isEmpty()) {
                response.setStatus(new RespStatus(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found"));
                return response;
            }
            for (Customer customer : customerList) {
                RespCustomer respCustomer = getCustomerById(customer.getId());
                respCustomerList.add(respCustomer);
            }
            response.setCustomerList(respCustomerList);
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
            return response;
        }

        return response;
    }

    @Override
    public RespCustomer getCustomerById(Long customerId) {

        RespCustomer response = new RespCustomer();

        try {
            if (customerId == null) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data"));
                return response;
            }

            Customer customer = customerDao.findByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());

            if (customer == null) {
                response.setStatus(new RespStatus(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found"));
                return response;
            }

            response.setCustomerId(customerId);
            response.setName(customer.getName());
            response.setSurname(customer.getSurname());
            response.setDob(customer.getDob());
            response.setAddress(customer.getAddress());
            response.setMobile(customer.getMobile());
            response.setGender(customer.getGender());
            response.setPassport(customer.getPassport());
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
            return response;
        }
        return response;
    }
}
