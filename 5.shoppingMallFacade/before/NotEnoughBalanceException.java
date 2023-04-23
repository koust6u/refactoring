package org.example.before;

public class NotEnoughBalanceException extends RuntimeException{
    private final Order order;

    public NotEnoughBalanceException(Order order){this.order = order;}

    public Order getOrder(){return order;}
}
