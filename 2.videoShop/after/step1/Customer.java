package step1;

import java.util.Enumeration;
import java.util.Vector;

class Customer {
    private String _name;
    private Vector<Rental> _rentals = new Vector();

    public Customer(String name) {
        _name = name;
    };

    public void addRental(Rental arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    };

    public String statement() {
        String result = "<H1>Rental Record for <EM>" + getName() + "</EM></H1><P>\n";
        for(Rental each: _rentals) {
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
        }
        result += "<P> You owe <EM>" + String.valueOf(getTotalCharge()) + "</EM><P>\n";
        result += "You earned <EM>" + String.valueOf(getTotalFrequentRenterPoints()) + "</EM> frequent renter points<P>";

        return result;
    }

    private int getTotalFrequentRenterPoints(){
        int result = 0;
        for(Rental each: _rentals) result += each.getFrequentRenterPoints();
        return result;
    }
    private double getTotalCharge(){
        double result = 0;
        for(Rental each: _rentals) result += each.getCharge();
        return result;
    }

}
