package deliveryApp.user;

public class Courier {
    private String courierName;
    private int courierId;
    private boolean isFree;

    private static int idCounter = 0;

    public Courier(String courierName) {
        this.courierName = courierName;
        this.isFree = true;
        this.courierId = idCounter++;
    }

    public String getCourierName() {
        return courierName;
    }

    public int getCourierId() {
        return courierId;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setIsFree(boolean isFree) {
        this.isFree = isFree;
    }
}
