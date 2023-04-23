package org.example.after;

import org.example.before.Customer;
import org.example.before.Order;

public class OrderProcessingFacade {
    private final BillingDepartment billingDepartment;
    private final OrderDepartment orderDepartment;
    private final ShippingDepartment shippingDepartment;

    public OrderProcessingFacade() {
        billingDepartment = new BillingDepartment();
        orderDepartment = new OrderDepartment();
        shippingDepartment = new ShippingDepartment();
    }

    public BillingDepartment getBillingDepartment() {
        return billingDepartment;
    }

    public OrderDepartment getOrderDepartment() {
        return orderDepartment;
    }

    public ShippingDepartment getShippingDepartment() {
        return shippingDepartment;
    }

    //subsystem1
    public static class BillingDepartment {
        public boolean makePayment(Order order, int price){
            int currentBalance = order.getCustomer().getBalance();
            if(!(currentBalance >= price)) return false;

            order.getCustomer().setBalance(currentBalance - price);
            order.setPaid(true);
            return true;
        }

        public void refund(Order order){
            int price = order.getPrice();
            int currentBalance = order.getCustomer().getBalance();
            order.getCustomer().setBalance(currentBalance + price);
        }
    }

    //subsystem2
    public static class ShippingDepartment {

        public void startShipping(Order order){
            String address = order.getCustomer().getAddress();
            String item = order.getItem();
            int quantity = order.getQuantity();

            System.out.println("Start shipping: " + item + ", Quantity: "+quantity +", Address: " + address);

        }

        public void stopShipping(Order order){
            String address = order.getCustomer().getAddress();
            String item = order.getItem();
            int quantity = order.getQuantity();

            System.out.println("Stop shipping: " + item + ", Quantity: " + quantity + ", Address: "+ address);
        }
    }

    //subsystem3
    public static class OrderDepartment {
        public Order makeOrder(Customer customer, String item, int quantity, boolean canBeCancelled){
            Order order = new Order(customer, item, quantity, canBeCancelled);
            return order;
        }

        public boolean cancelOrder(Order order){
            order.setCancelled(order.isCanBeCancelled());
            return order.isCanBeCancelled();
        }
    }

    public void processOrder(Customer customer, String item, int quantity) throws NotEnoughBalanceException {
        Order order = orderDepartment.makeOrder(customer, item, quantity, quantity >= 10);
        int price = order.getPrice();
        if (billingDepartment.makePayment(order, price)) {
            shippingDepartment.startShipping(order);
        } else {
            order.setPending(true);
            throw new NotEnoughBalanceException(order);
        }
    }

    public void cancelOrder(Order order){
        if(orderDepartment.cancelOrder(order)){
            billingDepartment.refund(order);
            shippingDepartment.stopShipping(order);
        }
        else{
            System.out.println("The order cannot be cancelled");
        }
    }
}
