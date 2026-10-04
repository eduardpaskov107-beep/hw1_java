package deliveryApp.order.deliveryMethods;

public class FastDelivery implements DeliveryMethod {
    @Override
    public int getDeliveryPrice(int quantity) {
        return quantity * 150;
    }
}
