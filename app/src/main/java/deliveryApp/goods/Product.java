package deliveryApp.goods;

public class Product {
  private int productId;
  private final String productName;
  private int price;
  private int quantity;

  public Product(String productName, int price, int quantity) {
    this.productName = productName;
    this.price = price;
    this.quantity = quantity;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public int getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return String.format("%d %s %d руб. (на складе: %d)", productId, productName, price, quantity);
  }
}
