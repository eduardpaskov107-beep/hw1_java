package deliveryApp.order;

public enum DeliveryStatus {
  processing("обрабатывается"),
  cooking("готовится"),
  onTheWay("в пути"),
  delivered("доставлен"),
  canceled("отменен");

  private final String description;

  DeliveryStatus(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public boolean canTransitionTo(DeliveryStatus next) {
    if (next == null) {
      return false;
    }
    return switch (this) {
      case processing -> next == cooking || next == canceled;
      case cooking -> next == onTheWay || next == canceled;
      case onTheWay -> next == delivered;
      case delivered, canceled -> false;
    };
  }
}
