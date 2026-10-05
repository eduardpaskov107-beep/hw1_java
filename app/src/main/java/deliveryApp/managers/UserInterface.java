package deliveryApp.managers;

import deliveryApp.goods.GoodsManager;
import deliveryApp.goods.Item;
import deliveryApp.goods.Product;
import deliveryApp.order.Order;
import deliveryApp.order.OrderManager;
import deliveryApp.order.deliveryMethods.DeliveryMethod;
import deliveryApp.order.deliveryMethods.FastDelivery;
import deliveryApp.order.deliveryMethods.PickupDelivery;
import deliveryApp.order.deliveryMethods.StandartDelivery;
import deliveryApp.user.User;


import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final GoodsManager goodsManager = new GoodsManager();
    private final OrderManager orderManager = new OrderManager();

    public static Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            greeting();
        }
    }

    private void greeting() {
        clearTerminal();
        System.out.println("""
                Кто ты, воин?
                1. Я пользователь
                2. Я админ магазина
                3. Я Ахиллес, сын Пелея
                0. Выйти
                """);

        String input = scanner.nextLine().trim();
        switch (input) {
            case "1" -> userMenu();
            case "2" -> adminMenu();
            case "3" -> {
                clearTerminal();
                System.out.println("Ты чего насмотрелся, что ты тут хотел увидеть?");
                pause();
            }
            case "0" -> {
                System.out.println("Бывай");
                System.exit(0);
            }
            default -> {
                System.out.println("Че читать не умеешь? Давай сначала");
                pause();
            }
        }
    }

    private void userMenu() {
        while (true) {
            clearTerminal();
            System.out.println("""
                    Ой, сарян, не признал братишку.
                    Что ты хочешь сделать?
                    1. Сделать заказ
                    2. Посмотреть текущие заказы
                    0. Назад
                    """);

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }
            switch (input) {
                case "1" -> createOrder();
                case "2" -> showOrders();
                case "0" -> {
                    return;
                }
                default -> {
                    System.out.println("Че жмешь");
                    pause();
                }
            }
        }
    }

    private void adminMenu() {
        while (true) {
            clearTerminal();
            System.out.println("""
                    Тут можно добавлять товары
                    1. Добавить товар
                    2. Показать товары
                    0. Назад
                    """);

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }
            switch (input) {
                case "1" -> addProduct();
                case "2" -> {
                    printProducts();
                    pause();
                }
                case "0" -> {
                    return;
                }
                default -> {
                    System.out.println("Криворукий");
                    pause();
                }
            }
        }
    }

    private void createOrder() {
        clearTerminal();

        System.out.print("Введите погоняло: ");
        String name = scanner.nextLine().trim();
        System.out.print("Введите адрес: ");
        String address = scanner.nextLine().trim();

        User user = new User(name, address);
        Order order = new Order(user);

        DeliveryMethod deliveryMethod = chooseDeliveryMethod();


        order.setDeliveryMethod(deliveryMethod);

        while (true) {
            clearTerminal();
            printProducts();
            System.out.println("\nНапиши номер товара, который надо тебе кинуть в корзину");
            System.out.println("0 - перейти в корзину");

            String productStr = scanner.nextLine().trim();
            int productNumber;
            try {
                productNumber = Integer.parseInt(productStr);
            } catch (Exception e) {
                System.out.println("Нужно ввести ЧИСЛО.");
                pause();
                continue;
            }

            if (productNumber == 0) {
                break;
            }

            List<Product> products = goodsManager.getProducts();
            if (productNumber < 1 || productNumber > products.size()) {
                System.out.println("Эжжи брат, товара с таким номером нет.");
                pause();
                continue;
            }

            Product selectedProduct = products.get(productNumber - 1);

            System.out.print("Сколько? ");
            int quantity;
            try {
                quantity = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Нужно ввести ЧИСЛО.");
                pause();
                continue;
            }

            if (quantity <= 0) {
                System.out.println("Количество должно быть БОЛЬШЕ нуля.");
                pause();
                continue;
            }

            Product orderProduct = new Product(
                    selectedProduct.getName(),
                    selectedProduct.getPrice()
            );

            Item item = new Item(orderProduct, quantity);
            order.addItem(item);

            System.out.println("Товар добавлен");
            pause();
        }

        orderManager.addOrder(order);

        clearTerminal();
        System.out.println("Заказ оформлен! Красаучик");
        printOrder(order);
        pause();
    }

    private DeliveryMethod chooseDeliveryMethod() {
        while (true) {
            clearTerminal();
            System.out.println("""
                    Выберите способ доставки:
                    1. Самовывоз - 0 руб.
                    2. Обычная доставка - 100 руб. за товар
                    3. Быстрая доставка - 150 руб. за товар
                    """);

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1" -> {
                    return new PickupDelivery();
                }
                case "2" -> {
                    return new StandartDelivery();
                }
                case "3" -> {
                    return new FastDelivery();
                }
                default -> {
                    System.out.println("Что ты делаешь");
                    pause();
                }
            }
        }
    }

    private void showOrders() {
        clearTerminal();
        List<Order> orders = orderManager.getOrders();

        if (orders.isEmpty()) {
            System.out.println("Заказов пока нет");
            pause();
            return;
        }

        for (Order order : orders) {
            printOrder(order);
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX \n");
        }
        pause();
    }

    private String getDeliveryMethod(DeliveryMethod deliveryMethod) {
        switch (deliveryMethod.getClass().getSimpleName()) {
            case "StandartDelivery" -> {
                return "Стандартная доставка";
            }
            case "FastDelivery" -> {
                return "Быстрая доставка";
            }
            default -> {
                return "Самовывоз";
            }
        }
    }

    private void printOrder(Order order) {
        System.out.println("Заказ №" + order.getOrderId());
        System.out.println("Адрес: " + order.getAddress());
        System.out.println("Статус: " + order.getStatus());
        if (order.getDeliveryMethod().isNeedCourier())
            System.out.println("Курьер: " + order.getCourier().getCourierName()
                    + " (рейтинг " + order.getCourier().getRating() + ")");
        System.out.println("Доставка: " + getDeliveryMethod(order.getDeliveryMethod()));
        System.out.println("Товары:");

        for (Item item : order.getItems()) {
            System.out.printf("  - %s x%d - %d. руб \n",
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getProduct().getPrice() * item.getQuantity());
        }

        System.out.printf("Итого: %d. руб \n", order.getPrice());
    }

    private void addProduct() {
        clearTerminal();

        System.out.print("Название товара: ");
        String name = scanner.nextLine().trim();

        int price;
        while (true) {
            System.out.print("Цена: ");
            try {
                price = Integer.parseInt(scanner.nextLine().trim());
                if (price < 0) {
                    System.out.println("Цена не может быть ОТРИЦАТЕЛЬНОЙ.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Нужно ввести ЦЕЛОЕ число.");
            }
        }

        goodsManager.addProduct(new Product(name, price));
        System.out.println("Товар добавлен");

        System.out.print("Добавить ещё один товар? 1 - да, 0 - нет: ");
        if (scanner.nextLine().trim().equals("1")) {
            addProduct();
        }
    }

    private void printProducts() {
        List<Product> products = goodsManager.getProducts();

        if (products.isEmpty()) {
            System.out.println("Товаров пока нет.");
            return;
        }

        System.out.println("Товары:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.printf("%d. %s - %d. руб \n",
                    i + 1,
                    product.getName(),
                    product.getPrice());
        }
    }

    private void pause() {
        System.out.println("\nНажми Enter, чтобы продолжить");
        scanner.nextLine();
    }

    private void clearTerminal() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}