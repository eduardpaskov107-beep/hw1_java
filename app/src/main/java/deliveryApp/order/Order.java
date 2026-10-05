package deliveryApp.order;


import deliveryApp.order.deliveryMethods.DeliveryMethod;
import deliveryApp.user.Courier;
import deliveryApp.user.User;

public class Order {
    private User user;
    private DeliveryMethod deliveryMethod;
    private int orderId;
    private OrderStatus status;
    private Courier courier;

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
}
