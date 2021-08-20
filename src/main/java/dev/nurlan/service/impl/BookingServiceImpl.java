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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger LOGGER = LogManager.getLogger(BookingServiceImpl.class);

    @Autowired
    private HttpServletRequest request;

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
            LOGGER.info("Ip: " + Utility.getClientIp(request) + ", called createBooking, reqBooking = " + reqBooking);
            Long customerId = reqBooking.getCustomerId();
            Long roomId = reqBooking.getRoomId();
            Integer bookingType = reqBooking.getBookingType();
            Date fromDate = reqBooking.getFromDate();
            Date toDate = reqBooking.getToDate();

            if (customerId == null || roomId == null || bookingType == null ||
                    fromDate == null || toDate == null) {
                response.setStatusCode(ExceptionConstants.INVALID_REQUEST_DATA);
                response.setStatusMessage("Invalid request data");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Invalid request data");
                return response;
            }

            Customer customer = customerDao.findByIdAndActive(customerId, EnumAvailableStatus.ACTIVE.getValue());
            if (customer == null) {
                response.setStatusCode(ExceptionConstants.CUSTOMER_NOT_FOUND);
                response.setStatusMessage("Customer not found");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Customer not found");
                return response;
            }

            Room room = roomDao.findByIdAndActive(roomId, EnumAvailableStatus.ACTIVE.getValue());
            if (room == null) {
                response.setStatusCode(ExceptionConstants.ROOM_NOT_FOUND);
                response.setStatusMessage("Room not found");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Room not found");
                return response;
            }

            if (!room.getRoomStatus().equals(EnumRoomStatus.EMPTY.getValue())) {
                response.setStatusCode(ExceptionConstants.ROOM_NOT_EMPTY);
                response.setStatusMessage("Room not empty");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Room not empty");
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
            LOGGER.warn("Ip: " + Utility.getClientIp(request) + "response: " + response);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(ExceptionConstants.INTERNAL_EXCEPTION);
            response.setStatusMessage("Internal exception");
            LOGGER.error("Ip: " + Utility.getClientIp(request) + ", error: " + e);
            return response;
        }
        return response;
    }

    @Override
    public RespStatus customerExitHotel(Long bookingId) {

        RespStatus response = new RespStatus();

        try {
            LOGGER.info("Ip: " + Utility.getClientIp(request) + ", called customerExitHotel, bookingId = " + bookingId);
            if (bookingId == null) {
                response.setStatusCode(ExceptionConstants.INVALID_REQUEST_DATA);
                response.setStatusMessage("Invalid request data");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Invalid request data");
                return response;
            }

            Booking booking = bookingDao.findByIdAndActive(bookingId, EnumAvailableStatus.ACTIVE.getValue());
            if (booking == null) {
                response.setStatusCode(ExceptionConstants.BOOKING_NOT_FOUND);
                response.setStatusMessage("Booking not found");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Booking not found");
                return response;
            }

            if (booking.getBookingType().equals(EnumBookingType.EXIT.getValue())) {
                response.setStatusCode(ExceptionConstants.CUSTOMER_EXITED_HOTEL);
                response.setStatusMessage("Customer exited hotel");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Customer exited hotel");
                return response;
            }

            Room room = roomDao.findByIdAndActive(booking.getRoom().getId(), EnumAvailableStatus.ACTIVE.getValue());
            if (room == null) {
                response.setStatusCode(ExceptionConstants.ROOM_NOT_FOUND);
                response.setStatusMessage("Room not found");
                LOGGER.info("Ip: " + Utility.getClientIp(request) + ", Room not found");
                return response;
            }

            Date exitDate = new Date();
            booking.setExitDate(exitDate);
            booking.setBookingType(EnumBookingType.EXIT.getValue());
            room.setRoomStatus(EnumRoomStatus.EMPTY.getValue());
            booking.setRoom(room);
            bookingDao.save(booking);
            response.setStatusCode(RespStatus.getSuccessMessage().getStatusCode());
            response.setStatusMessage(RespStatus.getSuccessMessage().getStatusMessage());
            LOGGER.warn("Ip: " + Utility.getClientIp(request) + "response: " + response);

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(ExceptionConstants.INTERNAL_EXCEPTION);
            response.setStatusMessage("Internal exception");
            LOGGER.error("Ip: " + Utility.getClientIp(request) + ", error: " + e);
            return response;
        }
        return response;
    }
}
