package dev.nurlan.service.impl;

import dev.nurlan.entity.Booking;
import dev.nurlan.entity.Customer;
import dev.nurlan.entity.Room;
import dev.nurlan.enums.EnumAvailableStatus;
import dev.nurlan.enums.EnumBookingType;
import dev.nurlan.enums.EnumRoomStatus;
import dev.nurlan.exception.ExceptionConstants;
import dev.nurlan.repository.BookingDao;
import dev.nurlan.repository.CustomerDao;
import dev.nurlan.repository.RoomDao;
import dev.nurlan.request.ReqBooking;
import dev.nurlan.response.RespStatus;
import dev.nurlan.service.BookingService;
import dev.nurlan.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookingServiceImpl implements BookingService {


    @Autowired
    private BookingDao bookingDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private RoomDao roomDao;


    @Override
    public RespStatus createBooking(ReqBooking reqBooking) {

        RespStatus response = new RespStatus();

        try {
            Long customerId = reqBooking.getCustomerId();
            Long roomId = reqBooking.getRoomId();
            Integer bookingType = reqBooking.getBookingType();
            Date fromDate = reqBooking.getFromDate();
            Date toDate = reqBooking.getToDate();

            if (customerId == null || roomId == null || bookingType == null ||
                    fromDate == null || toDate == null) {
                response.setStatusCode(ExceptionConstants.INVALID_REQUEST_DATA);
                response.setStatusMessage("Invalid request data");
                return response;
            }

            Customer customer = customerDao.findByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());
            if (customer == null) {
                response.setStatusCode(ExceptionConstants.CUSTOMER_NOT_FOUND);
                response.setStatusMessage("Customer not found");
                return response;
            }

            Room room = roomDao.findByIdAndActive(roomId, EnumAvailableStatus.ACTIVE.getValue());
            if (room == null) {
                response.setStatusCode(ExceptionConstants.ROOM_NOT_FOUND);
                response.setStatusMessage("Room not found");
                return response;
            }

            if (!room.getRoomStatus().equals(EnumRoomStatus.EMPTY.getValue())) {
                response.setStatusCode(ExceptionConstants.ROOM_NOT_EMPTY);
                response.setStatusMessage("Room not empty");
                return response;
            }

            if (bookingType.equals(EnumBookingType.REGISTRATION.getValue())) {
                room.setRoomStatus(EnumRoomStatus.FULL.getValue());
            } else {
                room.setRoomStatus(EnumRoomStatus.RESERVED.getValue());
            }

            long commonDayCount = Utility.getcommonPriceByDayCount(fromDate, toDate);
            Float commonPrice = room.getRoomPrice() * commonDayCount;

            Booking booking = new Booking();
            booking.setCustomer(customer);
            booking.setRoom(room);
            booking.setCommonPrice(commonPrice);
            booking.setBookingType(bookingType);
            booking.setFromDate(fromDate);
            booking.setToDate(toDate);
            bookingDao.save(booking);
            response.setStatusCode(RespStatus.getSuccessMessage().getStatusCode());
            response.setStatusMessage(RespStatus.getSuccessMessage().getStatusMessage());

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(ExceptionConstants.INTERNAL_EXCEPTION);
            response.setStatusMessage("Internal exception");
            return response;
        }
        return response;
    }

    @Override
    public RespStatus customerExitHotel(Long bookingId) {

        RespStatus response = new RespStatus();

        try {

            if (bookingId == null) {
                response.setStatusCode(ExceptionConstants.INVALID_REQUEST_DATA);
                response.setStatusMessage("Invalid request data");
                return response;
            }

            Booking booking = bookingDao.findByIdAndActive(bookingId, EnumAvailableStatus.ACTIVE.getValue());
            if (booking == null) {
                response.setStatusCode(ExceptionConstants.BOOKING_NOT_FOUND);
                response.setStatusMessage("Booking not found");
                return response;
            }

            Date exitDate = new Date();
            booking.setExitDate(exitDate);
            booking.setBookingType(EnumBookingType.EXIT.getValue());
            bookingDao.save(booking);
            response.setStatusCode(RespStatus.getSuccessMessage().getStatusCode());
            response.setStatusMessage(RespStatus.getSuccessMessage().getStatusMessage());

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(ExceptionConstants.INTERNAL_EXCEPTION);
            response.setStatusMessage("Internal exception");
            return response;
        }
        return response;
    }
}
