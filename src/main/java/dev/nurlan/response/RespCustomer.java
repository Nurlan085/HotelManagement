package dev.nurlan.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.Date;

@Data
@JacksonXmlRootElement
public class RespCustomer {

    private Long customerId;
    private String name;
    private String surname;
    private Date dob;
    private String address;
    private String mobile;
    private Integer gender;
    private String passport;
    private RespStatus status;

}
