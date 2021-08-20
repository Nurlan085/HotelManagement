package dev.nurlan.controller;


import dev.nurlan.request.ReqBooking;
import dev.nurlan.response.RespStatus;
import dev.nurlan.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/booking")
public class BookingController {


    @Autowired
    private BookingService bookingService;

    @PostMapping(value = "/createBooking")
    RespStatus createBooking(@RequestBody ReqBooking reqBooking) {
        return bookingService.createBooking(reqBooking);
    }
}
