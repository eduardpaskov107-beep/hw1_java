package deliveryApp.order.deliveryMethods;

public class FastDelivery implements DeliveryMethod {
    @Override
    public boolean isNeedCourier() {
        return true;
    }

    @Override
    public int getPrice() {
        return 150;
    }
}
