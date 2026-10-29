package deliveryApp.user;

import java.util.ArrayList;
import java.util.List;

public class CourierManager {
  private final List<Courier> couriers = new ArrayList<>();

  public CourierManager() {
    addCourier("Pi Diddy", 4.8);
    addCourier("Maduro", 4.5);
    addCourier("LeBron James", 5.0);
  }

  public Courier addCourier(String name, double rating) {
    Courier c = new Courier(name, rating);
    couriers.add(c);
    return c;
  }

  public boolean removeCourier(int id) {
    return couriers.removeIf(c -> c.getCourierId() == id && c.isFree());
  }

  public Courier getFreeCourier() {
    return couriers.stream().filter(Courier::isFree).findFirst().orElse(null);
  }

  public List<Courier> getCouriers() {
    return List.copyOf(couriers);
  }
}
