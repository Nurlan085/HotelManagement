package dev.nurlan.request;

import lombok.Data;

@Data
public class ReqRoom {

    private Long roomId;
    private String roomNo;
    private Long roomTypeId;
    //private Integer roomStatus;
    private Integer roomFloor;
    private Float roomPrice;
}
