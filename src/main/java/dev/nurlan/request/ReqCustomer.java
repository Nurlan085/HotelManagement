package dev.nurlan.request;


import lombok.Data;
import java.util.Date;

@Data
public class ReqCustomer {

    private Long customerId;
    private String name;
    private String surname;
    private Date dob;
    private String address;
    private String mobile;
    private Integer gender;
    private String passport;
}
