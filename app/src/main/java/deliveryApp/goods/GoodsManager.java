package deliveryApp.goods;

import java.util.ArrayList;
import java.util.List;

public class GoodsManager {
    private List<Product> products = new ArrayList<>(List.of(
            new Product("Монгольский краб", 1000),
            new Product("Карасик буль-буль", 400),
            new Product("Корнишон(не одинокий)", 200)
    ));

    public void addProduct(Product product) {
        products.add(product);
    }
    public void removeProduct(Product product) {
        products.remove(product);
    }
    public List<Product> getProducts() {
        return products;
    }
}
