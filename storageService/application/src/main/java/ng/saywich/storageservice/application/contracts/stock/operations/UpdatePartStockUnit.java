package ng.saywich.storageservice.application.contracts.stock.operations;

import ng.saywich.storageservice.domain.stock.PartStockUnit;

public final class UpdatePartStockUnit {
    private UpdatePartStockUnit() {}

    public record Request(PartStockUnit unit) {}

    public record Response(PartStockUnit unit) {}
}
