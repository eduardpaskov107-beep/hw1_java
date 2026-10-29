package deliveryApp.user;

public class User {
  private static int idCounter = 1;
  private final int userId;
  private final String userName;
  private String userAddress;

  public User(String userName, String userAddress) {
    this.userId = idCounter++;
    this.userName = userName;
    this.userAddress = userAddress;
  }

  public String getUserName() {
    return userName;
  }

  public String getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }
}
