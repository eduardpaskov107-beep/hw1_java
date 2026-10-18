package deliveryApp.goods;

public class Product {
    private String productName;
    private int quantity;
    private int price;

    public Product(String productName, int price,`int quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
}
