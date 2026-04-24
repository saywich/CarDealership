package ng.saywich.storageservice.application.contracts.stock.operations;

import ng.saywich.storageservice.domain.stock.PartStockUnit;

import java.util.List;
import java.util.UUID;

public final class QueryPartStockUnits {
    private QueryPartStockUnits() {}

    public record Request(UUID[] unitIds) {}

    public record Response(List<PartStockUnit> units) {}
}
