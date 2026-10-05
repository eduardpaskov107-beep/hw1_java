package deliveryApp.order;

public enum OrderStatus {
    CREATED("Создан"),
    IN_PROGRESS("В обработке"),
    IN_DELIVERY( "Доставляется"),
    DELIVERED("Вручен покупателю"),
    CANCELLED("Отменен");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
