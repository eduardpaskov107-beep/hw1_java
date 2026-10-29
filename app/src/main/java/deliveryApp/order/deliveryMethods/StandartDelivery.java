package deliveryApp.order.deliveryMethods;

public class StandartDelivery implements DeliveryMethod {
  @Override
  public int getDeliveryPrice(int quantity) {
    return quantity * 100;
  }

  @Override
  public boolean needCourier() {
    return true;
  }

  @Override
  public String getDeliveryName() {
    return "Стандартная";
  }
}
