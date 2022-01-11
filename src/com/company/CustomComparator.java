package com.company;

import java.util.Comparator;


class WorstPriceCustomComparator implements Comparator<Stock>{
    @Override
    public int compare(Stock o1, Stock o2) {
        if(o1.getPrice() < o2.getPrice()) {
            return -1;
        } else if(o1.getPrice() > o2.getPrice()) {
            return 1;
        }
        return 0;
    }
}

class BestPriceCustomComparator implements Comparator<Stock>{
    @Override
    public int compare(Stock o1, Stock o2) {
        if(o1.getPrice() > o2.getPrice()) {
            return -1;
        } else if(o1.getPrice() < o2.getPrice()) {
            return 1;
        }
        return 0;
    }
}

class BestChangeCustomComparator implements Comparator<Stock>{
    @Override
    public int compare(Stock o1, Stock o2) {
        if(o1.getPercentChange() > o2.getPercentChange()) {
            return -1;
        } else if(o1.getPercentChange() < o2.getPercentChange()) {
            return 1;
        }
        return 0;
    }
}
class WorstChangeCustomComparator implements Comparator<Stock>{
    @Override
    public int compare(Stock o1, Stock o2) {
        if(o1.getPercentChange() < o2.getPercentChange()) {
            return -1;
        } else if(o1.getPercentChange() > o2.getPercentChange()) {
            return 1;
        }
        return 0;
    }
}
