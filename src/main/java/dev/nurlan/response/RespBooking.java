package dev.nurlan.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.Date;

@Data
@JacksonXmlRootElement
public class RespBooking {

    private Long bookingId;
    private RespCustomer respCustomer;
    private RespRoom respRoom;
    private Float commonPrice;
    private Integer bookingType;
    private Date fromDate;
    private Date toDate;
}
