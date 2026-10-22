package deliveryApp.tools;

import deliveryApp.Console;
import deliveryApp.goods.GoodsManager;
import deliveryApp.goods.Product;
import deliveryApp.order.Order;
import deliveryApp.order.OrderManager;
import deliveryApp.user.UserManager;
import deliveryApp.order.deliveryMethods.DeliveryMethod;
import deliveryApp.order.deliveryMethods.FastDelivery;
import deliveryApp.order.deliveryMethods.PickupDelivery;
import deliveryApp.order.deliveryMethods.StandartDelivery;
import deliveryApp.user.Courier;
import deliveryApp.user.User;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserInterface {
    private final GoodsManager goodsManager;
    private final OrderManager orderManager;
    private final UserManager userManager;

    private final Map<Integer, Integer> cart = new LinkedHashMap<>();

    public UserInterface(GoodsManager gm, OrderManager om, UserManager um) {
        this.goodsManager = gm;
        this.orderManager = om;
        this.userManager = um;
    }

    public void start() {
        Console.clear();
        System.out.println("Вход / Регистрация");
        String name = Console.readLine("Введите имя: ");
        String address = Console.readLine("Введите адрес: ");
        User user = userManager.loginOrRegister(name, address);
        System.out.println("Добро пожаловать, " + user.getUserName());
        Console.pause();

        while (true) {
            Console.clear();
            System.out.println("""
                    Меню пользователя
                    1. Каталог товаров
                    2. Добавить товар в корзину
                    3. Посмотреть корзину
                    4. Оформить заказ
                    5. Мои заказы
                    6. Отменить заказ
                    0. Назад
                    """);
            switch (Console.readLine("> ")) {
                case "1" -> { showCatalog(); Console.pause(); }
                case "2" -> addToCart();
                case "3" -> { viewCart(); Console.pause(); }
                case "4" -> { checkout(user); Console.pause(); }
                case "5" -> { showMyOrders(user); Console.pause(); }
                case "6" -> { cancelMyOrder(user); Console.pause(); }
                case "0" -> { return; }
                default -> { System.out.println("Нет такого пункта"); Console.pause(); }
            }
        }
    }

    private void showCatalog() {
        List<Product> products = goodsManager.getProducts();
        System.out.println("Каталог");
        if (products.isEmpty()) { System.out.println("Пусто"); return; }
        for (Product p : products) System.out.println(p);
    }

    private void addToCart() {
        showCatalog();
        int id = Console.readInt("ID товара (0 - отмена): ");
        if (id == 0) return;
        Product p = goodsManager.findById(id);
        if (p == null) { System.out.println("Нет такого товара"); Console.pause(); return; }

        int qty = Console.readInt("Количество: ");
        if (qty <= 0) { System.out.println("Количество должно быть > 0"); Console.pause(); return; }

        int already = cart.getOrDefault(id, 0);
        if (already + qty > p.getQuantity()) {
            System.out.println("На складе только " + p.getQuantity() + " шт");
            Console.pause();
            return;
        }
        cart.merge(id, qty, Integer::sum);
        System.out.println("Добавлено в корзину");
        Console.pause();
    }

    private void viewCart() {
        if (cart.isEmpty()) { System.out.println("Корзина пуста"); return; }
        System.out.println("Корзина");
        int total = 0;
        for (var e : cart.entrySet()) {
            Product p = goodsManager.findById(e.getKey());
            int sum = p.getPrice() * e.getValue();
            total += sum;
            System.out.printf("%s x%d = %d руб %n", p.getProductName(), e.getValue(), sum);
        }
        System.out.println("Итого без доставки: " + total + " руб");
    }

    private void checkout(User user) {
        if (cart.isEmpty()) { System.out.println("Корзина пуста"); return; }
        viewCart();
        DeliveryMethod dm = chooseDeliveryMethod();
        try {
            Order order = orderManager.placeOrder(user, dm, cart);
            cart.clear();
            Console.clear();
            System.out.println("Заказ оформлен");
            printOrder(order);
        } catch (IllegalStateException e) {
            System.out.println("Не удалось оформить: " + e.getMessage());
        }
    }

    private DeliveryMethod chooseDeliveryMethod() {
        while (true) {
            Console.clear();
            System.out.println("""
                    Способ доставки:
                    1. Самовывоз       - 0 руб.
                    2. Обычная          - 100 руб
                    3. Быстрая          - 150 руб
                    """);
            switch (Console.readLine("> ")) {
                case "1" -> { return new PickupDelivery(); }
                case "2" -> { return new StandartDelivery(); }
                case "3" -> { return new FastDelivery(); }
                default  -> System.out.println("Нет такого пункта");
            }
        }
    }

    private void showMyOrders(User user) {
        List<Order> orders = orderManager.getOrdersForUser(user);
        if (orders.isEmpty()) { System.out.println("У вас ещё нет заказов"); return; }
        for (Order o : orders) {
            printOrder(o);
            System.out.println("------------------------------");
        }
    }

    private void cancelMyOrder(User user) {
        List<Order> orders = orderManager.getOrdersForUser(user);
        List<Order> cancellable = orders.stream()
                .filter(o -> o.getStatus() == deliveryApp.order.DeliveryStatus.processing
                        || o.getStatus() == deliveryApp.order.DeliveryStatus.cooking)
                .toList();
        if (cancellable.isEmpty()) {
            System.out.println("Нет заказов, доступных для отмены");
            return;
        }
        for (int i = 0; i < cancellable.size(); i++) {
            Order o = cancellable.get(i);
            System.out.printf("%d. Заказ №%d - %s%n",
                    i + 1, o.getOrderId(), o.getStatus().getDescription());
        }
        int idx = Console.readInt("Номер для отмены (0 - отмена): ");
        if (idx == 0) return;
        if (idx < 1 || idx > cancellable.size()) { System.out.println("Неверный номер"); return; }
        if (orderManager.cancelOrder(cancellable.get(idx - 1)))
            System.out.println("Заказ отменён, товары возвращены на склад");
        else
            System.out.println("Не удалось отменить");
    }

    private void printOrder(Order order) {
        System.out.println("Заказ №" + order.getOrderId());
        System.out.println("Адрес: " + order.getAddress());
        System.out.println("Статус: " + order.getStatus().getDescription());
        Courier c = order.getCourier();
        if (c != null) System.out.printf("Курьер: %s (рейтинг %.1f) ", c.getCourierName(), c.getRating());
        System.out.println("Доставка: " + deliveryMethodName(order.getDeliveryMethod()));
        System.out.println("Товары:");
        for (Product p : order.getProducts()) {
            System.out.printf("  - %s x%d - %d руб %n",
                    p.getProductName(), p.getQuantity(), p.getPrice() * p.getQuantity());
        }
        System.out.println("Итого: " + order.getPrice() + " руб");
    }

    private String deliveryMethodName(DeliveryMethod dm) {
        if (dm == null) return "не выбрана";
        return switch (dm.getClass().getSimpleName()) {
            case "StandartDelivery" -> "Стандартная";
            case "FastDelivery"     -> "Быстрая";
            default                 -> "Самовывоз";
        };
    }
}