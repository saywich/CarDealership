package ng.saywich.storageservice.application.contracts.stock.operations;

import ng.saywich.storageservice.domain.stock.StockCarReservation;

import java.util.UUID;

public final class QueryStockCarReservationByRequestId {
    private QueryStockCarReservationByRequestId() {}

    public record Request(UUID requestId) {}

    public record Response(StockCarReservation reservation) {}
}
