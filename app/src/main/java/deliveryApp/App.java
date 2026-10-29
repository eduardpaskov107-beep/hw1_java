package deliveryApp;

import deliveryApp.goods.GoodsManager;
import deliveryApp.order.OrderManager;
import deliveryApp.tools.AdminInterface;
import deliveryApp.tools.Console;
import deliveryApp.tools.UserInterface;
import deliveryApp.user.CourierManager;
import deliveryApp.user.UserManager;

public class App {
  private final GoodsManager goodsManager = new GoodsManager();
  private final CourierManager courierManager = new CourierManager();
  private final OrderManager orderManager = new OrderManager(goodsManager, courierManager);
  private final UserManager userManager = new UserManager();

  private final UserInterface userInterface =
      new UserInterface(goodsManager, orderManager, userManager);
  private final AdminInterface adminInterface =
      new AdminInterface(goodsManager, orderManager, courierManager);

  public void start() {
    while (true) {
      Console.clear();
      System.out.println(
          """
                    Кто ты, воин?
                    1. Я пользователь
                    2. Я админ магазина
                    0. Выйти
                    """);
      switch (Console.readLine("> ")) {
        case "1" -> userInterface.start();
        case "2" -> adminInterface.start();
        case "0" -> {
          System.out.println("Бывай");
          return;
        }
        default -> {
          System.out.println("Не понял");
          Console.pause();
        }
      }
    }
  }
}
