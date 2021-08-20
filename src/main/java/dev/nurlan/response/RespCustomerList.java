package dev.nurlan.response;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement
public class RespCustomerList {

    List<RespCustomer> customerList;
    RespStatus status;
}
