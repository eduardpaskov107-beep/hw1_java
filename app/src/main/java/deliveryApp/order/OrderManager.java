package deliveryApp.order;

import deliveryApp.user.Courier;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders = new ArrayList<>();
    private List<Courier> couriers = new ArrayList<>(List.of(
            new Courier("Pi Diddy"),
            new Courier("Maduro"),
            new Courier("LeBron James")
    ));

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    // null обрабатывать позже
    public Courier getCourier() {
        for (Courier courier : couriers) {
            if (courier.isFree()) {
                courier.setIsFree(false);
                return courier;
            }
        }
        return null;
    }
}
