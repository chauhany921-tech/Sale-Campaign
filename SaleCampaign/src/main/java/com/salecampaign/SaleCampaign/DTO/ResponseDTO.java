package com.salecampaign.SaleCampaign.DTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.List;

public class ResponseDTO {

    private String status;        // SUCCESS / ERROR
    private String message;       // human Readable
    private Object data;          // result data (optional)
    private List<String> errors;  // validation errors (optional)

    public ResponseDTO() {

    }

    public ResponseDTO(String status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.data = null;
        this.errors = errors;
    }

    public ResponseDTO(String success, String message,Object data) {
            this.status = success;
            this.data = data;
            this.message = message;
            this.errors = null;
    }



    public String getStatus() {
        return status;
    }

    public static ResponseDTO success(String message, Object data) {
        return new ResponseDTO("SUCCESS", message, data);
    }


    public static ResponseDTO error(String message, List<String> errors) {
        return new ResponseDTO("ERROR", message, errors);
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
