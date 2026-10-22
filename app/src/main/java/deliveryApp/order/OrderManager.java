package deliveryApp.order;

import deliveryApp.goods.GoodsManager;
import deliveryApp.goods.Product;
import deliveryApp.order.deliveryMethods.DeliveryMethod;
import deliveryApp.user.Courier;
import deliveryApp.user.CourierManager;
import deliveryApp.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderManager {
    private final List<Order> orders = new ArrayList<>();
    private final GoodsManager goodsManager;
    private final CourierManager courierManager;

    public OrderManager(GoodsManager goodsManager, CourierManager courierManager) {
        this.goodsManager = goodsManager;
        this.courierManager = courierManager;
    }

    public Order placeOrder(User user, DeliveryMethod dm, Map<Integer, Integer> cart) {
        if (cart.isEmpty()) throw new IllegalStateException("Корзина пуста");

        for (var e : cart.entrySet()) {
            Product p = goodsManager.findById(e.getKey());
            if (p == null) throw new IllegalStateException("Товар не найден: id=" + e.getKey());
        }

        Courier courier = courierManager.getFreeCourier();
        if (courier == null) throw new IllegalStateException("Нет свободных курьеров");

        Order order = new Order(user);
        order.setDeliveryMethod(dm);
        for (var e : cart.entrySet()) {
            Product src = goodsManager.findById(e.getKey());
            goodsManager.decreaseStock(e.getKey(), e.getValue());
            Product copy = new Product(src.getProductName(), src.getPrice(), e.getValue());
            copy.setProductId(src.getProductId());
            order.addProduct(copy);
        }
        courier.assignToOrder(order.getOrderId());
        order.setCourier(courier);
        orders.add(order);
        return order;
    }

    public boolean cancelOrder(Order order) {
        if (!order.changeStatus(DeliveryStatus.canceled)) return false;
        for (Product p : order.getProducts()) {
            goodsManager.increaseStock(p.getProductName(), p.getQuantity());
        }
        return true;
    }

    public boolean advanceStatus(Order order) {
        DeliveryStatus next = switch (order.getStatus()) {
            case processing -> DeliveryStatus.cooking;
            case cooking    -> DeliveryStatus.onTheWay;
            case onTheWay   -> DeliveryStatus.delivered;
            default         -> null;
        };
        return next != null && order.changeStatus(next);
    }

    public List<Order> getOrders() { return orders; }

    public List<Order> getOrdersForUser(User user) {
        return orders.stream()
                .filter(o -> o.getUser().getUserName().equalsIgnoreCase(user.getUserName()))
                .toList();
    }
}