package ng.saywich.storageservice.application.contracts.stock.operations;

import ng.saywich.storageservice.domain.stock.StockPartReservation;

import java.util.List;
import java.util.UUID;

public final class QueryStockPartReservationByPartId {
    private QueryStockPartReservationByPartId() {}

    public record Request(UUID partId) {}

    public record Response(List<StockPartReservation> reservations) {}
}
