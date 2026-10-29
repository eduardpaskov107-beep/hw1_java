package deliveryApp.tools;

import java.util.Scanner;

public final class Console {
  public static final Scanner SCANNER = new Scanner(System.in);

  private Console() {}

  public static void clear() {
    for (int i = 0; i < 50; i++) {
      System.out.println();
    }
  }

  public static void pause() {
    System.out.print("\nНажми Enter, чтобы продолжить...");
    SCANNER.nextLine();
  }

  public static String readLine(String prompt) {
    System.out.print(prompt);
    return SCANNER.nextLine().trim();
  }

  public static int readInt(String prompt) {
    while (true) {
      System.out.print(prompt);
      try {
        return Integer.parseInt(SCANNER.nextLine().trim());
      } catch (NumberFormatException e) {
        System.out.println("Нужно ввести ЦЕЛОЕ число");
      }
    }
  }
}
