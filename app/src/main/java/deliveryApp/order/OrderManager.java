package deliveryApp.order;

import java.util.List;

public class OrderManager {
    private List<Order> orders;

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public  getCourier() {
    }
}
