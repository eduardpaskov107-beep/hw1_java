package deliveryApp.user;

public class Courier {
    private String courierName;
    private int courierId;
    private int rating;

    private static int idCounter = 0;

    public Courier(String courierName, int rating) {
        this.courierName = courierName;
        this.rating = rating;
        this.courierId = idCounter++;
    }

    public String getCourierName() {
        return courierName;
    }

    public int getCourierId() {
        return courierId;
    }

    public int getRating() {
        return rating;
    }
}
