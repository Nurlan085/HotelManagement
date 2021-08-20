package dev.nurlan.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import dev.nurlan.entity.Customer;
import dev.nurlan.entity.Room;
import lombok.Data;

import java.util.Date;

@Data
@JacksonXmlRootElement
public class RespBooking {

    private Long bookingId;
    private Customer customer;
    private Room room;
    private Float commonPrice;
    private Integer bookingType;
    private Date fromDate;
    private Date toDate;
}
