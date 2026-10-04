package deliveryApp.order.deliveryMethods;

public class StandartDelivery implements DeliveryMethod {
    @Override
    public int getDeliveryPrice(int quantity) {
        return quantity * 100;
    }
}
