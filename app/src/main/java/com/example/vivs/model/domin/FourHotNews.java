package com.example.vivs.model.domin;

public class FourHotNews {
    private int code;
    private Data data;
    private String message;
    private boolean ok;

    @Override
    public String toString() {
        return "FourHotNews{" +
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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
