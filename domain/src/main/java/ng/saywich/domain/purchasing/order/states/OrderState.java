package ng.saywich.domain.purchasing.order.states;

public enum OrderState {
    PENDING,
    ACCEPTED,
    WAITING_FOR_PAYMENT,
    PAID,
    WAITING_FOR_DELIVERY,
    READY,
    COMPLETED,
    CANCELLED
}
