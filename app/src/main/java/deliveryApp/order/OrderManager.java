package deliveryApp.order;

import deliveryApp.goods.Product;
import deliveryApp.user.Courier;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private List<Order> orders = new ArrayList<>();
    private List<Courier> couriers = new ArrayList<>(List.of(
            new Courier("Pi Diddy", 5),
            new Courier("Maduro", 3),
            new Courier("LeBron James", 4)
    ));
    private static int counter = 0;

    public void addOrder(Order order) {
        orders.add(order);
    }
    public void removeOrder(Order order) {
        orders.remove(order);
    }
    public List<Order> getOrders() {
        return orders;
    }
    public Courier getCourier() {
        Courier courier = couriers.get(counter % 3);
        counter++;
        return courier;
    }
}
