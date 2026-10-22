package deliveryApp.user;

public class Courier {
    private static int idCounter = 1;

    private final int courierId;
    private String courierName;
    private double rating;
    private boolean isFree;
    private Integer currentOrderId;

    public Courier(String courierName, double rating) {
        this.courierId = idCounter++;
        this.courierName = courierName;
        this.rating = rating;
        this.isFree = true;
    }

    public int getCourierId() { return courierId; }
    public String getCourierName() { return courierName; }
    public double getRating() { return rating; }
    public boolean isFree() { return isFree; }
    public Integer getCurrentOrderId() { return currentOrderId; }

    public void setRating(double rating) { this.rating = rating; }

    public void assignToOrder(int orderId) {
        this.isFree = false;
        this.currentOrderId = orderId;
    }

    public void free() {
        this.isFree = true;
        this.currentOrderId = null;
    }

    @Override
    public String toString() {
        return String.format("%s (рейтинг %.1f, %s)", courierName, rating,
                isFree ? "свободен" : "занят заказом №" + currentOrderId);
    }
}