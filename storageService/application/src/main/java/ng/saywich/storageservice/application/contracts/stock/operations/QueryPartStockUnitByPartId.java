package ng.saywich.storageservice.application.contracts.stock.operations;

import ng.saywich.storageservice.domain.stock.PartStockUnit;

import java.util.UUID;

public final class QueryPartStockUnitByPartId {
    private QueryPartStockUnitByPartId() {}

    public record Request(UUID partId) {}

    public record Response(PartStockUnit unit) {}
}
