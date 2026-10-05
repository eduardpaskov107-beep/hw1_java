package deliveryApp.order;


import deliveryApp.goods.Item;
import deliveryApp.order.deliveryMethods.DeliveryMethod;
import deliveryApp.user.Courier;
import deliveryApp.user.User;

import java.util.List;

public class Order {
    private User user;
    private DeliveryMethod deliveryMethod;
    private int orderId;
    private OrderStatus status;
    private Courier courier;
    private int quantity;
    private List<Item> items;

    public Order(User user, ) {

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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public List<Item> getItems() {
        return this.items;
    }

    public int getPrice() {

    }
}
