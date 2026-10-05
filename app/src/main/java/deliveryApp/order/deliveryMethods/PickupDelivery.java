package deliveryApp.order.deliveryMethods;

public class PickupDelivery implements DeliveryMethod {
    @Override
    public boolean isNeedCourier() {
        return false;
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
