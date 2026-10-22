package deliveryApp.order;

import deliveryApp.goods.Product;
import deliveryApp.order.deliveryMethods.DeliveryMethod;
import deliveryApp.user.Courier;
import deliveryApp.user.User;

import java.util.ArrayList;

public class Order {
    private static int idCounter = 1;

    private final int orderId;
    private final User user;
    private final ArrayList<Product> products = new ArrayList<>();
    private final String address;
    private DeliveryMethod deliveryMethod;
    private DeliveryStatus status;
    private Courier courier;

    public Order(User user) {
        this.orderId = idCounter++;
        this.user = user;
        this.address = user.getUserAddress();
        this.status = DeliveryStatus.processing;
    }

    public void addProduct(Product product) {
        for (Product p : products) {
            if (p.getProductName().equalsIgnoreCase(product.getProductName())) {
                p.setQuantity(p.getQuantity() + product.getQuantity());
                return;
            }
        }
        products.add(product);
    }

    public boolean changeStatus(DeliveryStatus next) {
        if (!status.canTransitionTo(next)) return false;
        status = next;
        if ((next == DeliveryStatus.delivered || next == DeliveryStatus.canceled)
                && courier != null) {
            courier.free();
        }
        return true;
    }

    public int getPrice() {
        int price = 0;
        for (Product p : products) price += p.getPrice() * p.getQuantity();
        if (deliveryMethod != null) price += deliveryMethod.getDeliveryPrice(products.size());
        return price;
    }

    public int getOrderId() { return orderId; }
    public User getUser() { return user; }
    public String getAddress() { return address; }
    public DeliveryMethod getDeliveryMethod() { return deliveryMethod; }
    public DeliveryStatus getStatus() { return status; }
    public Courier getCourier() { return courier; }
    public ArrayList<Product> getProducts() { return products; }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
    public void setCourier(Courier courier) { this.courier = courier; }
}