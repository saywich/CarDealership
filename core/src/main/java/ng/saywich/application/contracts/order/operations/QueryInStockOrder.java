package ng.saywich.application.contracts.order.operations;

import ng.saywich.application.contracts.dtos.purchasing.InStockOrderDto;
import ng.saywich.application.contracts.order.operations.models.OrderFilter;

import java.util.List;
import java.util.UUID;

public final class QueryInStockOrder {
    private QueryInStockOrder() {}

    public record Request(UUID[] ids, OrderFilter filter) {}

    public record Response(List<InStockOrderDto> orders) {}
}
