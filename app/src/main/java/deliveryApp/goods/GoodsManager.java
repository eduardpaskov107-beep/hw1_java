package deliveryApp.goods;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GoodsManager {
  private final Map<Integer, Product> products = new LinkedHashMap<>();
  private int idCounter = 1;

  public GoodsManager() {
    addProduct("Монгольский краб", 1000, 10);
    addProduct("Карасик буль-буль", 400, 5);
    addProduct("Корнишон(не одинокий)", 200, 10);
  }

  public Product addProduct(String name, int price, int quantity) {
    for (Product p : products.values()) {
      if (p.getProductName().equalsIgnoreCase(name)) {
        p.setQuantity(p.getQuantity() + quantity);
        if (price > 0) {
          p.setPrice(price);
        }
        return p;
      }
    }
    Product p = new Product(name, price, quantity);
    p.setProductId(idCounter++);
    products.put(p.getProductId(), p);
    return p;
  }

  public boolean removeProduct(int id) {
    return products.remove(id) != null;
  }

  public Product findById(int id) {
    return products.get(id);
  }

  public Product findByName(String name) {
    for (Product p : products.values()) {
      if (p.getProductName().equalsIgnoreCase(name)) {
        return p;
      }
    }

    return null;
  }

  public List<Product> getProducts() {
    return List.copyOf(products.values());
  }

  public boolean decreaseStock(int id, int qty) {
    Product p = products.get(id);
    if (p == null || p.getQuantity() < qty) {
      return false;
    }

    p.setQuantity(p.getQuantity() - qty);
    return true;
  }

  public void increaseStock(String name, int qty) {
    Product p = findByName(name);
    if (p != null) {
      p.setQuantity(p.getQuantity() + qty);
    }
  }
}
