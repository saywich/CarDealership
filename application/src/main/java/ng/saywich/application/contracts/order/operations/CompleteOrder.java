package ng.saywich.application.contracts.order.operations;

import ng.saywich.domain.purchasing.order.states.OrderState;

import java.util.UUID;

public final class CompleteOrder {
    private CompleteOrder() {}

    public record Request(UUID orderId) {}

    public record Response(UUID orderId, OrderState state) {}
}
