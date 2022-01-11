package com.company;

public class Stock {
    private String capRank;
    private String companyName;
    private String ticker;
    private double price;
    private double change;
    private double percentChange;

    public Stock(String capRank, String companyName, String ticker, double price, double change, double percentChange) {
        this.capRank = capRank;
        this.companyName = companyName;
        this.ticker = ticker;
        this.price = price;
        this.change = change;
        this.percentChange = percentChange;
    }

    public String getCapRank() {
        return capRank;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getTicker() {
        return ticker;
    }

    public double getPrice() {
        return price;
    }

    public double getChange() {
        return change;
    }

    public double getPercentChange() {
        return percentChange;
    }

    public void setCapRank(String capRank) {
        this.capRank = capRank;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public void setPercentChange(double percentChange) {
        this.percentChange = percentChange;
    }

}

