package com.example.vivs.model.domin;

public class AODLmsg {
    private int code;
    private news news;
    private String message;
    private boolean ok;

    @Override
    public String toString() {
        return "NewsData{" +
                "code=" + code +
                ", data=" + news +
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

    public news getData() {
        return news;
    }

    public void setData(news news) {
        this.news = news;
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
