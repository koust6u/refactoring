package step2.price;

import step2.Movie;
import step2.price.Price;

public class NewReleasePrice extends Price {

    public int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    public double getCharge(int daysRented) {
        return daysRented*3;
    }
}
