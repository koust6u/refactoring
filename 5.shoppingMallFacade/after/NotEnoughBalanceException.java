package org.example.after;

import org.example.before.Order;

public class NotEnoughBalanceException extends RuntimeException{
    private final Order order;

    public NotEnoughBalanceException(Order order){this.order = order;}

    public Order getOrder(){return order;}
}
