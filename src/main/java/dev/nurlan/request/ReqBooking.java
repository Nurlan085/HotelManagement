package dev.nurlan.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

@Data
public class ReqBooking  {

    private Long bookingId;
    private Long customerId;
    private Long roomId;
    //private Float commonPrice;
    private Integer bookingType;
    @JsonFormat(pattern="dd MM yyyy")
    private Date fromDate;
    @JsonFormat(pattern="dd MM yyyy")
    private Date toDate;

}
