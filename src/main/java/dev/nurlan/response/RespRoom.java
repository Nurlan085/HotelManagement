package dev.nurlan.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement
public class RespRoom {

    private Long roomId;
    private String roomNo;
    @JsonProperty(value = "roomType")
    private RespRoomType respRoomType;
    private Integer roomStatus;
    private Integer roomFloor;
    private Float roomPrice;
    private RespStatus status;

}
