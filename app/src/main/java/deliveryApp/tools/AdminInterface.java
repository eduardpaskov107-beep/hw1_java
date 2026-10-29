package deliveryApp.tools;

import deliveryApp.goods.GoodsManager;
import deliveryApp.goods.Product;
import deliveryApp.order.Order;
import deliveryApp.order.OrderManager;
import deliveryApp.user.Courier;
import deliveryApp.user.CourierManager;
import java.util.List;

public class AdminInterface {
  private final GoodsManager goodsManager;
  private final OrderManager orderManager;
  private final CourierManager courierManager;

  public AdminInterface(GoodsManager gm, OrderManager om, CourierManager cm) {
    this.goodsManager = gm;
    this.orderManager = om;
    this.courierManager = cm;
  }

  public void start() {
    while (true) {
      Console.clear();
      System.out.println(
          """
                    Админ меню
                    1. Товары
                    2. Курьеры
                    3. Все заказы
                    4. Продвинуть статус заказа
                    0. Назад
                    """);
      switch (Console.readLine("> ")) {
        case "1" -> productsMenu();
        case "2" -> couriersMenu();
        case "3" -> {
          showAllOrders();
          Console.pause();
        }
        case "4" -> {
          advanceOrder();
          Console.pause();
        }
        case "0" -> {
          return;
        }
        default -> {
          System.out.println("Нет такого пункта");
          Console.pause();
        }
      }
    }
  }

  private void productsMenu() {
    while (true) {
      Console.clear();
      System.out.println(
          """
                    Товары
                    1. Добавить
                    2. Удалить
                    3. Изменить цену
                    4. Показать все
                    0. Назад
                    """);
      switch (Console.readLine("> ")) {
        case "1" -> addProduct();
        case "2" -> removeProduct();
        case "3" -> changePrice();
        case "4" -> {
          printProducts();
          Console.pause();
        }
        case "0" -> {
          return;
        }
        default -> {
          System.out.println("?");
          Console.pause();
        }
      }
    }
  }

  private void addProduct() {
    String name = Console.readLine("Название: ");
    int price = Console.readInt("Цена: ");
    int qty = Console.readInt("Количество: ");
    if (price < 0 || qty < 0) {
      System.out.println("Отрицательные значения нельзя");
      Console.pause();
      return;
    }
    goodsManager.addProduct(name, price, qty);
    System.out.println("Товар добавлен/обновлён");
    Console.pause();
  }

  private void removeProduct() {
    printProducts();
    int id = Console.readInt("ID для удаления (0 - отмена): ");
    if (id == 0) {
      return;
    }
    System.out.println(goodsManager.removeProduct(id) ? "Удалено" : "Не найдено");
    Console.pause();
  }

  private void changePrice() {
    printProducts();
    int id = Console.readInt("ID товара: ");
    Product p = goodsManager.findById(id);
    if (p == null) {
      System.out.println("Не найден");
      Console.pause();
      return;
    }
    int price = Console.readInt("Новая цена: ");
    if (price < 0) {
      System.out.println("Цена < 0");
      Console.pause();
      return;
    }
    p.setPrice(price);
    System.out.println("Цена обновлена");
    Console.pause();
  }

  private void printProducts() {
    List<Product> products = goodsManager.getProducts();
    if (products.isEmpty()) {
      System.out.println("Пусто");
      return;
    }
    for (Product p : products) {
      System.out.println(p);
    }
  }

  private void couriersMenu() {
    while (true) {
      Console.clear();
      System.out.println(
          """
                    Курьеры
                    1. Добавить
                    2. Удалить (только свободного)
                    3. Показать всех
                    0. Назад
                    """);
      switch (Console.readLine("> ")) {
        case "1" -> addCourier();
        case "2" -> removeCourier();
        case "3" -> {
          printCouriers();
          Console.pause();
        }
        case "0" -> {
          return;
        }
        default -> {
          System.out.println("?");
          Console.pause();
        }
      }
    }
  }

  private void addCourier() {
    String name = Console.readLine("Имя: ");
    System.out.print("Рейтинг (0–5): ");
    double rating;
    try {
      rating = Double.parseDouble(Console.SCANNER.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("Не число");
      Console.pause();
      return;
    }

    courierManager.addCourier(name, Math.max(0, Math.min(5, rating)));
    System.out.println("Курьер добавлен");
    Console.pause();
  }

  private void removeCourier() {
    printCouriers();
    int id = Console.readInt("ID курьера (0 - отмена): ");
    if (id == 0) {
      return;
    }
    System.out.println(courierManager.removeCourier(id) ? "Удалён" : "Не найден или занят");
    Console.pause();
  }

  private void printCouriers() {
    for (Courier c : courierManager.getCouriers()) {
      System.out.printf("%d %s%n", c.getCourierId(), c);
    }
  }

  private void showAllOrders() {
    List<Order> orders = orderManager.getOrders();
    if (orders.isEmpty()) {
      System.out.println("Заказов нет");
      return;
    }

    for (Order o : orders) {
      System.out.printf(
          "№%d | %s | %s | %d руб. | курьер: %s%n",
          o.getOrderId(),
          o.getUser().getUserName(),
          o.getStatus().getDescription(),
          o.getPrice(),
          o.getCourier() == null ? "—" : o.getCourier().getCourierName());
    }
  }

  private void advanceOrder() {
    showAllOrders();
    int id = Console.readInt("ID заказа (0 - отмена): ");
    if (id == 0) {
      return;
    }

    // аналог LINQ из C#, чем то похоже на sql запросы
    // sream оборачивает список в конвейер чтобы можно было использовать фильтры и тд
    Order target =
        orderManager.getOrders().stream()
            .filter(o -> o.getOrderId() == id)
            .findFirst()
            .orElse(null);
    if (target == null) {
      System.out.println("Не найден");
      return;
    }

    if (orderManager.advanceStatus(target)) {
      System.out.println("Новый статус: " + target.getStatus().getDescription());
    } else {
      System.out.println("Дальше двигать нельзя");
    }
  }
}
