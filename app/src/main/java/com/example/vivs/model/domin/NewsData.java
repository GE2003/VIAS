package com.example.vivs.model.domin;

import java.util.List;

public class NewsData {
    private int code;
    private data data;
    private String message;
    private boolean ok;

    @Override
    public String toString() {
        return "NewsData{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                ", ok=" + ok +
                '}';
    }

    public com.example.vivs.model.domin.data getData() {
        return data;
    }

    public void setData(com.example.vivs.model.domin.data data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
