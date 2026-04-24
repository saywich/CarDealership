package ng.saywich.orderservice.application.contracts.order.operations.models;

import java.util.UUID;

public record OrderFilter(
    UUID customerId,
    UUID managerId
) { }
