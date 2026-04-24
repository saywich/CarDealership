package ng.saywich.storageservice.application.contracts.stock.operations;

import ng.saywich.storageservice.domain.stock.StockCarReservation;

import java.util.List;
import java.util.UUID;

public final class QueryStockCarReservations {
    private QueryStockCarReservations() {}

    public record Request(UUID[] reservationIds) {}

    public record Response(List<StockCarReservation> reservations) {}
}
