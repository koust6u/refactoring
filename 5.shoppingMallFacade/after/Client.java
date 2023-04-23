package org.example.after;

import org.example.before.*;

import java.util.Scanner;

public class Client {
    OrderProcessingFacade orderProcessingFacade = new OrderProcessingFacade();
    private final Scanner scanner = new Scanner(System.in);
    private final Customer customer1 = new Customer("park", "Busan", 10000);
    private Order order;

    public void makeOrder(){
        String item = scanner.next();
        int quantity = scanner.nextInt();

        try {
            orderProcessingFacade.processOrder(customer1,item,quantity);
        }
        catch (NotEnoughBalanceException e){
            System.out.println("You have not enough money to buy " + order.getItem());
        }
    }


    public void cancelOrder(){
        System.out.println("Are you sure to cancel? ");
        String answer = scanner.next();
        if(!answer.equalsIgnoreCase("Y")) return;
        orderProcessingFacade.cancelOrder(order);
    }
}