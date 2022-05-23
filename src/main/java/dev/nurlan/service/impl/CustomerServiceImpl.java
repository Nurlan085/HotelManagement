package dev.nurlan.service.impl;

import dev.nurlan.entity.Customer;
import dev.nurlan.enums.EnumAvailableStatus;
import dev.nurlan.exception.ExceptionConstants;
import dev.nurlan.repository.CustomerDao;
import dev.nurlan.request.ReqCustomer;
import dev.nurlan.response.RespCustomer;
import dev.nurlan.response.RespCustomerList;
import dev.nurlan.response.RespStatus;
import dev.nurlan.service.CustomerService;
import dev.nurlan.util.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CustomerDao customerDao;


    @Override
    public RespCustomerList getCustomerList() {

        RespCustomerList response = new RespCustomerList();

        try {
            LOGGER.info("Ip: " + Utility.getClientIp(request) + ", called getCustomerList");
            //List<RespCustomer> respCustomerList = new ArrayList<>();

            List<Customer> customerList = customerDao.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
            if (customerList.isEmpty()) {
                response.setStatus(new RespStatus(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found"));
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Customer not found");
                return response;
            }

            List<RespCustomer> respCustomerList = customerList
                    .stream()
                    .map(data ->
                            getCustomerById(data.getId()))
                    .collect(Collectors.toList());

            response.setCustomerList(respCustomerList);
            response.setStatus(RespStatus.getSuccessMessage());
            LOGGER.warn("Ip: " + Utility.getClientIp(request) + ", success");

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
            LOGGER.error("Ip: " + Utility.getClientIp(request) + ", error: " + e);

            return response;
        }

        return response;
    }

    @Override
    public RespCustomer getCustomerById(Long customerId) {

        RespCustomer response = new RespCustomer();

        try {
            LOGGER.info("Ip: " + Utility.getClientIp(request) + ", called getCustomerById, customerId = " + customerId);

            if (customerId == null) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data"));
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Invalid request data");
                return response;
            }

            Customer customer = customerDao.findByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());
            if (customer == null) {
                response.setStatus(new RespStatus(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found"));
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Customer not found");
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
            LOGGER.warn("Ip: " + Utility.getClientIp(request) + "response: " + response);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal exception"));
            LOGGER.error("Ip: " + Utility.getClientIp(request) + ", error: " + e);
            return response;
        }
        return response;
    }

    @Override
    public RespStatus updateCustomer(ReqCustomer reqCustomer) {

        RespStatus response = new RespStatus();

        try {
            LOGGER.info("Ip: " + Utility.getClientIp(request) + ", called updateCustomer, reqCustomer = " + reqCustomer);

            Long customerId = reqCustomer.getCustomerId();
            String name = reqCustomer.getName();
            String surname = reqCustomer.getSurname();
            Date dob = reqCustomer.getDob();
            String address = reqCustomer.getAddress();
            String mobile = reqCustomer.getMobile();
            Integer gender = reqCustomer.getGender();
            String passport = reqCustomer.getPassport();

            if (customerId == null || (name == null || name.isEmpty()) || (surname == null || surname.isEmpty()) || dob == null ||
                    (address == null || address.isEmpty()) || (mobile == null || mobile.isEmpty()) ||
                    gender == null || (passport == null || passport.isEmpty())) {
                response.setStatusCode(ExceptionConstants.INVALID_REQUEST_DATA);
                response.setStatusMessage("Invalid request data");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Invalid request data");
                return response;
            }

            Customer customer = customerDao.findByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());
            if (customer == null) {
                response.setStatusCode(ExceptionConstants.CUSTOMER_NOT_FOUND);
                response.setStatusMessage("Customer not found");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Customer not found");
                return response;
            }

            customer.setName(name);
            customer.setSurname(surname);
            customer.setDob(dob);
            customer.setAddress(address);
            customer.setMobile(mobile);
            customer.setGender(gender);
            customer.setPassport(passport);
            customerDao.save(customer);
            response.setStatusCode(RespStatus.getSuccessMessage().getStatusCode());
            response.setStatusMessage(RespStatus.getSuccessMessage().getStatusMessage());
            LOGGER.warn("Ip: " + Utility.getClientIp(request) + "response: " + response);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(ExceptionConstants.INTERNAL_EXCEPTION);
            response.setStatusMessage("Internal exception");
            LOGGER.error("Ip: " + Utility.getClientIp(request) + ", error: " + e);
            return response;
        }
        return response;
    }

    @Override
    public RespStatus createCustomer(ReqCustomer reqCustomer) {

        RespStatus response = new RespStatus();

        try {
            LOGGER.info("Ip: " + Utility.getClientIp(request) + ", called createCustomer, reqCustomer = " + reqCustomer);

            String name = reqCustomer.getName();
            String surname = reqCustomer.getSurname();
            Date dob = reqCustomer.getDob();
            String address = reqCustomer.getAddress();
            String mobile = reqCustomer.getMobile();
            Integer gender = reqCustomer.getGender();
            String passport = reqCustomer.getPassport();

            if ((name == null || name.isEmpty()) || (surname == null || surname.isEmpty()) /*|| dob == null*/ ||
                    (address == null || address.isEmpty()) || (mobile == null || mobile.isEmpty()) ||
                    gender == null || (passport == null || passport.isEmpty())) {
                response.setStatusCode(ExceptionConstants.INVALID_REQUEST_DATA);
                response.setStatusMessage("Invalid request data");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Invalid request data");
                return response;
            }

            Customer customer = new Customer();
            customer.setName(name);
            customer.setSurname(surname);
            customer.setDob(reqCustomer.getDob());
            customer.setAddress(reqCustomer.getAddress());
            customer.setMobile(reqCustomer.getMobile());
            customer.setGender(reqCustomer.getGender());
            customer.setPassport(passport);
            customerDao.save(customer);
            response.setStatusCode(RespStatus.getSuccessMessage().getStatusCode());
            response.setStatusMessage(RespStatus.getSuccessMessage().getStatusMessage());
            LOGGER.warn("Ip: " + Utility.getClientIp(request) + "response: " + response);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(ExceptionConstants.INTERNAL_EXCEPTION);
            response.setStatusMessage("Internal exception");
            LOGGER.error("Ip: " + Utility.getClientIp(request) + ", error: " + e);
            return response;
        }
        return response;
    }

    @Override
    public RespStatus deleteCustomer(Long customerId) {

        RespStatus response = new RespStatus();

        try {
            LOGGER.info("Ip: " + Utility.getClientIp(request) + ", called deleteCustomer, customerId = " + customerId);


            if (customerId == null) {
                response.setStatusCode(ExceptionConstants.INVALID_REQUEST_DATA);
                response.setStatusMessage("Invalid request data");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Invalid request data");
                return response;
            }

            Customer customer = customerDao.findByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());

            if (customer == null) {
                response.setStatusCode(ExceptionConstants.CUSTOMER_NOT_FOUND);
                response.setStatusMessage("Customer not found");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Customer not found");
                return response;
            }

            customer.setActive(EnumAvailableStatus.DEACTIVE.getValue());
            customerDao.save(customer);
            response.setStatusCode(RespStatus.getSuccessMessage().getStatusCode());
            response.setStatusMessage(RespStatus.getSuccessMessage().getStatusMessage());
            LOGGER.warn("Ip: " + Utility.getClientIp(request) + "response: " + response);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(ExceptionConstants.INTERNAL_EXCEPTION);
            response.setStatusMessage("Internal exception");
            LOGGER.error("Ip: " + Utility.getClientIp(request) + ", error: " + e);
            return response;
        }
        return response;
    }
}
