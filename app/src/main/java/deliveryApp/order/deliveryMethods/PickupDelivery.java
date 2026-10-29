package deliveryApp.order.deliveryMethods;

public class PickupDelivery implements DeliveryMethod {
  @Override
  public int getDeliveryPrice(int quantity) {
    return 0;
  }

  @Override
  public boolean needCourier() {
    return false;
  }

  @Override
  public String getDeliveryName() {
    return "Самовывоз";
  }
}
