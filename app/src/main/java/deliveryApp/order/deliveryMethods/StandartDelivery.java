package deliveryApp.order.deliveryMethods;

public class StandartDelivery implements DeliveryMethod {
    @Override
    public boolean isNeedCourier() {
        return true;
    }

    @Override
    public int getPrice() {
        return 100;
    }
}
