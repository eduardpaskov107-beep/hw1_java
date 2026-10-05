package deliveryApp.order;

import deliveryApp.user.Courier;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders = new ArrayList<>();
    private static int idx = -1;
    private static int id = -1;
    private List<Courier> couriers = new ArrayList<>(List.of(
            new Courier("Pi Diddy", 5),
            new Courier("Maduro", 3),
            new Courier("LeBron James", 4)
    ));

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public Courier getCourier() {
        idx += 1;
        return this.couriers.get(idx%this.couriers.size());
    }

    public int getNewOrderId() {
        id += 1;
        return id;
    }
}
