package com.homify.bo;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * The base response for return the json request from the controllers.
 * Created by swapnil.gupta on 3/25/17.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse extends Exception {
    private int statusCode;
    private String statusMessage;
    private Object data;

    public BaseResponse(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

}
