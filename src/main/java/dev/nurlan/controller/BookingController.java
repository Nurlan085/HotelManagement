package dev.nurlan.controller;


import dev.nurlan.request.ReqBooking;
import dev.nurlan.response.RespStatus;
import dev.nurlan.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/booking")
public class BookingController {


    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "/createBooking")
    RespStatus createBooking(@RequestBody ReqBooking reqBooking) {
        return bookingService.createBooking(reqBooking);
    }

    @PostMapping(value = "/customerExitHotel/{bookingId}")
    public RespStatus customerExitHotel(@PathVariable("bookingId") Long bookingId) {
        return bookingService.customerExitHotel(bookingId);
    }

}
