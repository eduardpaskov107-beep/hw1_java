package deliveryApp.order.deliveryMethods;

public interface DeliveryMethod {
  int getDeliveryPrice(int quantity);

  boolean needCourier();

  String getDeliveryName();
}
