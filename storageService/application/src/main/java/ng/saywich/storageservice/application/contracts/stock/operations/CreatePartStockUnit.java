package ng.saywich.storageservice.application.contracts.stock.operations;

import ng.saywich.storageservice.domain.stock.PartStockUnit;

public final class CreatePartStockUnit {
    private CreatePartStockUnit() {}

    public record Request(PartStockUnit unit) {}

    public record Response(PartStockUnit unit) {}
}
