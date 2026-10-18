package deliveryApp.order;

public enum deliveryStatus {
    processing("обрабатывается"),
    cooking("готовится"),
    onTheWay("в пути"),
    delivered("доставлен"),
    canceled("отменен");

    private final String description;

    private deliveryStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
