package ng.saywich.orderservice.application.contracts.dtos.purchasing;

import ng.saywich.shared.domain.purchasing.order.states.OrderState;

import java.util.UUID;

public record ManufactureOrderDto(
    UUID orderId,
    OrderState orderState,
    ManagerDto manager,
    CustomerDto customer,
    UUID carModelId,
    UUID optionSetId
) { }
