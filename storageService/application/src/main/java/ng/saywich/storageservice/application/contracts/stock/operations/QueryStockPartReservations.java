package ng.saywich.storageservice.application.contracts.stock.operations;

import ng.saywich.storageservice.domain.stock.StockPartReservation;

import java.util.List;
import java.util.UUID;

public final class QueryStockPartReservations {
    private QueryStockPartReservations() {}

    public record Request(UUID[] reservationIds) {}

    public record Response(List<StockPartReservation> reservations) {}
}
