package ng.saywich.orderservice.application.contracts.order.operations;

import ng.saywich.shared.domain.purchasing.order.states.OrderState;

import java.util.UUID;

public final class WaitForDeliveryOrder {
    private WaitForDeliveryOrder() {}

    public record Request(UUID orderId) {}

    public record Response(UUID orderId, OrderState state) {}
}
