package deliveryApp.order;

import deliveryApp.user.Courier;

import java.util.List;

public class OrderManager {
    private List<Order> orders;
    private Courier courier;

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public Courier getCourier() {
        return this.courier;
    }
}
