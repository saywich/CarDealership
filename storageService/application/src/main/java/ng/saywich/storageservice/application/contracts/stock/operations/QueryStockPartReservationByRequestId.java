package ng.saywich.storageservice.application.contracts.stock.operations;

import ng.saywich.storageservice.domain.stock.StockPartReservation;

import java.util.List;
import java.util.UUID;

public final class QueryStockPartReservationByRequestId {
    private QueryStockPartReservationByRequestId() {}

    public record Request(UUID requestId) {}

    public record Response(List<StockPartReservation> reservations) {}
}
