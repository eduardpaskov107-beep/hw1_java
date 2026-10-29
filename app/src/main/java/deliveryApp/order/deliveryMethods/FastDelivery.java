package deliveryApp.order.deliveryMethods;

public class FastDelivery implements DeliveryMethod {
  @Override
  public int getDeliveryPrice(int quantity) {
    return quantity * 150;
  }

  @Override
  public boolean needCourier() {
    return true;
  }

  @Override
  public String getDeliveryName() {
    return "Быстрая";
  }
}
