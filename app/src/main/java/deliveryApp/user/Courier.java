package deliveryApp.user;

public class Courier {
    private String courierName;
    private int rating;

    public Courier(String name, int rating) {
        this.courierName = name;
        this.rating = rating;
    }

    public String getCourierName() {
        return courierName;
    }

    public int getRating() {
        return this.rating;
    }
}
