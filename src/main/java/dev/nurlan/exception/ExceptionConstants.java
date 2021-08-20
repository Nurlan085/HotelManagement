package dev.nurlan.exception;

import lombok.Data;

@Data
public class ExceptionConstants {

    public static final Integer INTERNAL_EXCEPTION = 100;

    public static final Integer DATA_NOT_FOUND = 101;

    public static final Integer INVALID_REQUEST_DATA = 102;

    public static final Integer CUSTOMER_NOT_FOUND = 103;

    public static final Integer ROOM_NOT_FOUND = 104;

    public static final Integer ROOM_TYPE_NOT_FOUND = 105;

    public static final Integer ROOM_NOT_EMPTY = 106;

    public static final Integer BOOKING_NOT_FOUND = 107;

    public static final Integer CUSTOMER_EXITED_HOTEL = 108;

}
