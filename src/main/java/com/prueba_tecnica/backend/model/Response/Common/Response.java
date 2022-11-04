package com.prueba_tecnica.backend.model.Response.Common;

public class Response {
    private Integer Code;
    private String Message;
    private Object Data;

    public  Response() {
        this.Code = 400;
        this.Message = "";
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }
}
