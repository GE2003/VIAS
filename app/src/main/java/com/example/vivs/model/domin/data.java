package com.example.vivs.model.domin;

import java.util.ArrayList;

public class data {
    public ArrayList<news> records;

    @Override
    public String toString() {
        return "data{" +
                "records=" + records +
                '}';
    }

    public ArrayList<news> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<news> records) {
        this.records = records;
    }
}
