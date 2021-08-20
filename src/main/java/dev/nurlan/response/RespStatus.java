package dev.nurlan.response;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement
public class RespStatus {

    private Integer statusCode;
    private String statusMessage;

    private static final Integer SUCCESS_CODE = 1;
    private static final String SUCCES_MESSAGE = "success";

    public static RespStatus getSuccessMessage() {
        return new RespStatus(SUCCESS_CODE, SUCCES_MESSAGE);
    }

}
