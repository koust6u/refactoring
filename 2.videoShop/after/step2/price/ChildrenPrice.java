package step2.price;

import step1.Movie;

public class ChildrenPrice extends Price {
    public int getPriceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    public double getCharge(int daysRented) {
        double result = 1.5;
        if(daysRented > 3) result += (daysRented -3 )* 1.5;
        return result;
    }
    public int getFrequentRenterPoints(int daysRented){
        return (daysRented > 1) ? 2: 1;
    }
}
