package deliveryApp.order;

import deliveryApp.goods.Product;
import deliveryApp.order.deliveryMethods.DeliveryMethod;
import deliveryApp.user.Courier;
import deliveryApp.user.User;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private User user;
    private ArrayList<Product> products = new ArrayList<>();
    private String address;
    private int price;
    private DeliveryMethod deliveryMethod;
    private String status;
    private Courier courier;

    private static int idCounter = 0;

    public Order(User user, Courier courier) {
        orderId = idCounter++;
        this.user = user;
        address = user.getUserAddress();
        status = "Готовится";
        this.courier = courier;
    }

    public void addProduct(Product product) {
        for (Product p : products) {
            if (p.getProductName().equals(product.getProductName())) {
                p.setQuantity(p.getQuantity() + product.getQuantity());
                return;
            }
        }
        products.add(product);
    }

    public void removeProduct(int productId) {
        products.remove(productId);
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getAddress() {
        return address;
    }

    // цена формируется от цены товаров + цена доставки,
    // которая зависит от типа и количества
    public int getPrice() {
        price = 0;
        for (Product product : products) {
            price += product.getQuantity() * product.getPrice();
        }
        price += deliveryMethod.getDeliveryPrice(products.size());
        return price;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public String getStatus() {
        return status;
    }

    public Courier getCourier() {
        return courier;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public User getUser() {
        return user;
    }
}
