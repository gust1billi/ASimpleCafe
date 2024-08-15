package com.example.asimplecafe.model;

import java.util.Date;

public class Receipt {
    int id, profit; String date;

    public Receipt(int id, int profit, String date) {
        this.id = id;
        this.profit = profit;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getProfit() {
        return profit;
    }

    public String getDate() {
        return date;
    }
}
