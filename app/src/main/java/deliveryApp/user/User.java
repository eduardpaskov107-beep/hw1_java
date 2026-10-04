package deliveryApp.user;

public class User {
    private String userName;
    private String userAddress;

    public User(String userName, String userEmail) {
        this.userName = userName;
        this.userAddress = userEmail;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserAddress() {
        return userAddress;
    }
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
