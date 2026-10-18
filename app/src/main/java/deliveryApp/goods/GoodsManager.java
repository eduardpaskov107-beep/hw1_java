package deliveryApp.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsManager {
    //хэщ мапа это стильно можно молодежно
    private Map<String, Product> products = new HashMap<>(Map.of(
            "Монгольский краб", new Product("Монгольский краб", 1000, 10),
            "Карасик буль-буль", new Product("Карасик буль-буль", 400, 5),
            "Корнишон(не одинокий)", new Product("Корнишон(не одинокий)", 200, 10)
    ));

    public void addProduct(Product product) {
        if (products.containsKey(product.getProductName())) {
            Product oldProduct = products.get(product.getProductName());
            oldProduct.setQuantity(oldProduct.getQuantity() + product.getQuantity());
        }
        products.put(product.getProductName(), product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public Map<String, Product> getProducts() {
        return products;
    }
}
