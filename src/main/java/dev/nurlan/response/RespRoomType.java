package dev.nurlan.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement
public class RespRoomType {

    private Long roomTypeId;
    private String name;
    private String description;
}
