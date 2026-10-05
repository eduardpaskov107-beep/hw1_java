package deliveryApp.goods;

import java.util.ArrayList;
import java.util.List;

public class GoodsManager {
    private List<Product> products = new ArrayList<>(List.of(
            new Product("Монгольский краб", 1000),
            new Product("Карасик буль-буль", 400),
            new Product("Корнишон(не одинокий)", 200)
    ));
}
