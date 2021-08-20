package dev.nurlan.service;

import dev.nurlan.request.ReqBooking;
import dev.nurlan.response.RespStatus;

public interface BookingService {

    RespStatus createBooking(ReqBooking reqBooking);
}
