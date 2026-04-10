package ng.saywich.application.contracts.order.operations;

import ng.saywich.application.contracts.dtos.purchasing.ManufactureOrderDto;
import ng.saywich.application.contracts.order.operations.models.OrderFilter;

import java.util.List;
import java.util.UUID;

public final class QueryManufactureOrder {
    private QueryManufactureOrder() {}

    public record Request(UUID[] ids, OrderFilter filter) {}

    public record Response(List<ManufactureOrderDto> orders) {}
}