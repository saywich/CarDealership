package ng.saywich.storageservice.application.contracts.stock.operations;

import ng.saywich.storageservice.domain.stock.PartStockUnit;

import java.util.UUID;

public final class DeletePartStockUnit {
    private DeletePartStockUnit() {}

    public record Request(UUID unitId) {}

    public record Response(PartStockUnit unit) {}
}
