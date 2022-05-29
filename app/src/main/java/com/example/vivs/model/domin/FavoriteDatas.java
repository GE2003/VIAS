package com.example.vivs.model.domin;

public class FavoriteDatas {
    private int code;
    private Object data;
    private  String message;
    private  boolean ok;

    @Override
    public String toString() {
        return "FavoriteDatas{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                ", ok=" + ok +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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
