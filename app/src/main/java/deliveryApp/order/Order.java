package deliveryApp.order;
import deliveryApp.goods.Item;
import deliveryApp.order.deliveryMethods.DeliveryMethod;
import deliveryApp.user.Courier;
import deliveryApp.user.User;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private User user;
    private DeliveryMethod deliveryMethod;
    private int orderId;
    private OrderStatus status;
    private Courier courier;
    private List<Item> items = new ArrayList<>();
    private String address;

    public Order(User user, int id) {
        this.address = user.getAddress();
        this.orderId = id;
        this.status = OrderStatus.CREATED;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod){
        this.deliveryMethod = deliveryMethod;
    }

    public DeliveryMethod getDeliveryMethod() {
        return this.deliveryMethod;
    }

    public Courier getCourier() {
        return this.courier;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public List<Item> getItems() {
        return this.items;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public String getAddress() {
        return this.address;
    }

    public String getStatus() {
        return this.status.getDescription();
    }

    public long getPrice() {
        long sum = 0;
        for (Item item : this.items) {
            sum += (long) item.getProduct().getPrice() * item.getQuantity();
        }
        sum += (long) this.deliveryMethod.getPrice() * this.items.size();
        return sum;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }
}
