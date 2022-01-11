package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
    //marketIndex() for overall performance of the S&P 500 of today.
    //top500Search(ticker symbol as a String) to find the performance of a specific stock on the S&P 500 today.
            //ex. top500Search("AAPL"); ---> Apple Inc. | $175.10 | 2.91 | 1.69%
    //top500LowestPrice() to order all the stocks on the S&P 500 from lowest to highest price per share.
            //Lowest price per share --> Highest Price Per Share
    //top500HighestPrice() to order all the stocks on the S&P 500 from highest to lowest price per share.
            //Highest Price Per Share --> Lowest Price Per Share
    //top500WorstPerforming() to analyze the worst performing stocks of the S&P 500 today based on % change.
            // Worst Performing Stocks --> Best Performing Stocks in order
    //top500BestPerforming() to analyze the best performing stocks on the S&P 500 today based on % change.
            // Best Performing Stocks --> Worst Performing Stocks in order
    //top500() to list all the stocks of the S&P 500.
            // Highest Capsize --> Lowest Capsize
    //NOTE: THIS IS MY FIRST PROJECT, I'm just a college freshman who just finished a basic intro to CS course. ENJOY!
        //This project utilizes concepts of OOP, Web Scraping, basic data structures, abstract data types, and comparators.
        //May modify to have a GUI in the future. Also could make a separate program for the NASDAQ Composite Index.
    }
    public static void marketIndex() {
        String url = "https://www.slickcharts.com/sp500";
        try {
            Document indexes = Jsoup.connect(url).get();
            for(Element row: indexes.select("table.table-hover.table-borderless.table-sm tr")) {
                if(row.select("td.text-nowrap:nth-of-type(1)").text().equals("S&P 500")) {
                    String indexName = row.select("td.text-nowrap:nth-of-type(1)").text();
                    String tempIndexSize = row.select("td.text-nowrap:nth-of-type(2)").text();
                    tempIndexSize = tempIndexSize.replace(",", "");
                    double indexSize = Double.parseDouble(tempIndexSize);
                    double indexChange = Double.parseDouble(row.select("td.text-nowrap:nth-of-type(3)").text());
                    String tempIndexPercentChange = row.select("td.text-nowrap:nth-of-type(4)").text();
                    tempIndexPercentChange = tempIndexPercentChange.replace("%", "");
                    tempIndexPercentChange = tempIndexPercentChange.replace("(", "");
                    tempIndexPercentChange = tempIndexPercentChange.replace(")", "");
                    double indexPercentChange = Double.parseDouble(tempIndexPercentChange);
                    System.out.println(indexName + " | " + indexSize + " | " + indexChange + " | " + indexPercentChange + "%");
                }
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void top500Search(String tickerSymbol) {
        tickerSymbol = tickerSymbol.toUpperCase();
        HashMap<String, Stock> stockHashMap = new HashMap<String, Stock>();
        String url = "https://www.slickcharts.com/sp500";
        try {
            Document stocks = Jsoup.connect(url).get();
            for(Element row: stocks.select("table.table.table-hover.table-borderless.table-sm tr")) {
                if(row.select("td:nth-of-type(2)").text().equals("")) {
                    continue;
                } else {
                    String capRank = row.select("td:nth-of-type(1)").text();
                    String companyName = row.select("td:nth-of-type(2)").text();
                    String ticker = row.select("td:nth-of-type(3)").text();
                    String tempPrice = row.select("td.text-nowrap:nth-of-type(5)").text();
                    tempPrice = tempPrice.replace(",", "");
                    if(tempPrice.equals("")) {
                        continue;
                    }
                    double price = Double.parseDouble(tempPrice);
                    String tempChange = row.select("td.text-nowrap:nth-of-type(6)").text();
                    double change = Double.parseDouble(tempChange);
                    String tempPercentChange = row.select("td.text-nowrap:nth-of-type(7)").text();
                    tempPercentChange = tempPercentChange.replace("(", "");
                    tempPercentChange = tempPercentChange.replace(")", "");
                    tempPercentChange = tempPercentChange.replace("%", "");
                    double percentChange = Double.parseDouble(tempPercentChange);
                    Stock stock = new Stock(capRank, companyName, ticker, price, change, percentChange);
                    stockHashMap.put(ticker, stock);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        if(stockHashMap.containsKey(tickerSymbol)) {
            System.out.println(stockHashMap.get(tickerSymbol).getCompanyName() + " | $" + new DecimalFormat("0.00").format(stockHashMap.get(tickerSymbol).getPrice()) + " | " +
                    new DecimalFormat("0.00").format(stockHashMap.get(tickerSymbol).getChange()) + " | " + stockHashMap.get(tickerSymbol).getPercentChange() + "%");
        } else {
            System.out.println("Stock cannot be found");
        }
    }
    public static void top500LowestPrice() {
        LinkedList<Stock> stockList = new LinkedList<Stock>();
        String url = "https://www.slickcharts.com/sp500";
        try {
            Document stocks = Jsoup.connect(url).get();
            for(Element row: stocks.select("table.table.table-hover.table-borderless.table-sm tr")) {
                if(row.select("td:nth-of-type(2)").text().equals("")) {
                    continue;
                } else {
                    String capRank = row.select("td:nth-of-type(1)").text();
                    String companyName = row.select("td:nth-of-type(2)").text();
                    String ticker = row.select("td:nth-of-type(3)").text();
                    String tempPrice = row.select("td.text-nowrap:nth-of-type(5)").text();
                    tempPrice = tempPrice.replace(",", "");
                    if(tempPrice.equals("")) {
                        continue;
                    }
                    double price = Double.parseDouble(tempPrice);
                    String tempChange = row.select("td.text-nowrap:nth-of-type(6)").text();
                    double change = Double.parseDouble(tempChange);
                    String tempPercentChange = row.select("td.text-nowrap:nth-of-type(7)").text();
                    tempPercentChange = tempPercentChange.replace("(", "");
                    tempPercentChange = tempPercentChange.replace(")", "");
                    tempPercentChange = tempPercentChange.replace("%", "");
                    double percentChange = Double.parseDouble(tempPercentChange);
                    Stock stock = new Stock(capRank, companyName, ticker, price, change, percentChange);
                    stockList.addLast(stock);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        Collections.sort(stockList, new WorstPriceCustomComparator());
        for(int i = 0; i < stockList.size(); i++) {
            System.out.println(i+1 + " | " + stockList.get(i).getCompanyName() + " | " + stockList.get(i).getTicker() + " | $" + new DecimalFormat("0.00").format(stockList.get(i).getPrice()) + " | "
                    + new DecimalFormat("0.00").format(stockList.get(i).getChange()) + " | " + stockList.get(i).getPercentChange() + "%");
        }
    }
    public static void top500HighestPrice() {
        LinkedList<Stock> stockList = new LinkedList<Stock>();
        String url = "https://www.slickcharts.com/sp500";
        try {
            Document stocks = Jsoup.connect(url).get();
            for(Element row: stocks.select("table.table.table-hover.table-borderless.table-sm tr")) {
                if(row.select("td:nth-of-type(2)").text().equals("")) {
                    continue;
                } else {
                    String capRank = row.select("td:nth-of-type(1)").text();
                    String companyName = row.select("td:nth-of-type(2)").text();
                    String ticker = row.select("td:nth-of-type(3)").text();
                    String tempPrice = row.select("td.text-nowrap:nth-of-type(5)").text();
                    tempPrice = tempPrice.replace(",", "");
                    if(tempPrice.equals("")) {
                        continue;
                    }
                    double price = Double.parseDouble(tempPrice);
                    String tempChange = row.select("td.text-nowrap:nth-of-type(6)").text();
                    double change = Double.parseDouble(tempChange);
                    String tempPercentChange = row.select("td.text-nowrap:nth-of-type(7)").text();
                    tempPercentChange = tempPercentChange.replace("(", "");
                    tempPercentChange = tempPercentChange.replace(")", "");
                    tempPercentChange = tempPercentChange.replace("%", "");
                    double percentChange = Double.parseDouble(tempPercentChange);
                    Stock stock = new Stock(capRank, companyName, ticker, price, change, percentChange);
                    stockList.addLast(stock);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        Collections.sort(stockList, new BestPriceCustomComparator());
        for(int i = 0; i < stockList.size(); i++) {
            System.out.println(i+1 + " | " + stockList.get(i).getCompanyName() + " | " + stockList.get(i).getTicker() + " | $" + new DecimalFormat("0.00").format(stockList.get(i).getPrice()) + " | "
                    + new DecimalFormat("0.00").format(stockList.get(i).getChange()) + " | " + stockList.get(i).getPercentChange() + "%");
        }
    }
    public static void top500WorstPerforming() {
        LinkedList<Stock> stockList = new LinkedList<Stock>();
        String url = "https://www.slickcharts.com/sp500";
        try {
            Document stocks = Jsoup.connect(url).get();
            for(Element row: stocks.select("table.table.table-hover.table-borderless.table-sm tr")) {
                if(row.select("td:nth-of-type(2)").text().equals("")) {
                    continue;
                } else {
                    String capRank = row.select("td:nth-of-type(1)").text();
                    String companyName = row.select("td:nth-of-type(2)").text();
                    String ticker = row.select("td:nth-of-type(3)").text();
                    String tempPrice = row.select("td.text-nowrap:nth-of-type(5)").text();
                    tempPrice = tempPrice.replace(",", "");
                    if(tempPrice.equals("")) {
                        continue;
                    }
                    double price = Double.parseDouble(tempPrice);
                    String tempChange = row.select("td.text-nowrap:nth-of-type(6)").text();
                    double change = Double.parseDouble(tempChange);
                    String tempPercentChange = row.select("td.text-nowrap:nth-of-type(7)").text();
                    tempPercentChange = tempPercentChange.replace("(", "");
                    tempPercentChange = tempPercentChange.replace(")", "");
                    tempPercentChange = tempPercentChange.replace("%", "");
                    double percentChange = Double.parseDouble(tempPercentChange);
                    Stock stock = new Stock(capRank, companyName, ticker, price, change, percentChange);
                    stockList.addLast(stock);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        Collections.sort(stockList, new WorstChangeCustomComparator());
        for(int i = 0; i < stockList.size(); i++) {
            System.out.println(i+1 + " | " + stockList.get(i).getCompanyName() + " | " + stockList.get(i).getTicker() + " | $" + new DecimalFormat("0.00").format(stockList.get(i).getPrice()) + " | "
                    + new DecimalFormat("0.00").format(stockList.get(i).getChange()) + " | " + stockList.get(i).getPercentChange() + "%");
        }

    }

    public static void top500BestPerforming() {
        LinkedList<Stock> stockList = new LinkedList<Stock>();
        String url = "https://www.slickcharts.com/sp500";
        try {
            Document stocks = Jsoup.connect(url).get();
            for(Element row: stocks.select("table.table.table-hover.table-borderless.table-sm tr")) {
                if(row.select("td:nth-of-type(2)").text().equals("")) {
                    continue;
                } else {
                    String capRank = row.select("td:nth-of-type(1)").text();
                    String companyName = row.select("td:nth-of-type(2)").text();
                    String ticker = row.select("td:nth-of-type(3)").text();
                    String tempPrice = row.select("td.text-nowrap:nth-of-type(5)").text();
                    tempPrice = tempPrice.replace(",", "");
                    if(tempPrice.equals("")) {
                        continue;
                    }
                    double price = Double.parseDouble(tempPrice);
                    String tempChange = row.select("td.text-nowrap:nth-of-type(6)").text();
                    double change = Double.parseDouble(tempChange);
                    String tempPercentChange = row.select("td.text-nowrap:nth-of-type(7)").text();
                    tempPercentChange = tempPercentChange.replace("(", "");
                    tempPercentChange = tempPercentChange.replace(")", "");
                    tempPercentChange = tempPercentChange.replace("%", "");
                    double percentChange = Double.parseDouble(tempPercentChange);
                    Stock stock = new Stock(capRank, companyName, ticker, price, change, percentChange);
                    stockList.addLast(stock);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        Collections.sort(stockList, new BestChangeCustomComparator());
        for(int i = 0; i < stockList.size(); i++) {
            System.out.println(i+1 + " | " + stockList.get(i).getCompanyName() + " | " + stockList.get(i).getTicker() + " | $" + new DecimalFormat("0.00").format(stockList.get(i).getPrice()) + " | "
                    + new DecimalFormat("0.00").format(stockList.get(i).getChange()) + " | " + stockList.get(i).getPercentChange() + "%");
        }

    }
    public static void top500() {
        String url = "https://www.slickcharts.com/sp500";
        try {
            Document stocks = Jsoup.connect(url).get();
            for(Element row: stocks.select("table.table.table-hover.table-borderless.table-sm tr")) {
                if(row.select("td:nth-of-type(2)").text().equals("")) {
                    continue;
                } else {
                    String capRank = row.select("td:nth-of-type(1)").text();
                    String companyName = row.select("td:nth-of-type(2)").text();
                    String ticker = row.select("td:nth-of-type(3)").text();
                    String tempPrice = row.select("td.text-nowrap:nth-of-type(5)").text();
                    tempPrice = tempPrice.replace(",", "");
                    if(tempPrice.equals("")) {
                        continue;
                    }
                    double price = Double.parseDouble(tempPrice);
                    String tempChange = row.select("td.text-nowrap:nth-of-type(6)").text();
                    double change = Double.parseDouble(tempChange);
                    String tempPercentChange = row.select("td.text-nowrap:nth-of-type(7)").text();
                    tempPercentChange = tempPercentChange.replace("(", "");
                    tempPercentChange = tempPercentChange.replace(")", "");
                    tempPercentChange = tempPercentChange.replace("%", "");
                    double percentChange = Double.parseDouble(tempPercentChange);
                    System.out.println(capRank + " | " + companyName + " | " + ticker + " | $" + new DecimalFormat("0.00").format(price) + " | "
                            + new DecimalFormat("0.00").format(change) + " | " + percentChange + "%");
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
