package com.example.asimplecafe.model;

import java.util.Date;

public class Receipt {
    // DENGAN ASUMSI GROSS PROFIT, BARANG YG DIJUAL SEMUANYA SBG PROFIT
    int id, paid, profit, kembalian; String date;

    public Receipt(int id, int paid, int profit, int kembalian, String date) {
        this.id = id;
        this.paid = paid;
        this.profit = profit;
        this.kembalian = kembalian;
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
