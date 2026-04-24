package ng.saywich.storageservice.application.contracts.stock.operations;

import ng.saywich.storageservice.domain.stock.StockCarReservation;

import java.util.UUID;

public final class QueryStockCarReservationByCarId {
    private QueryStockCarReservationByCarId() {}

    public record Request(UUID carId) {}

    public record Response(StockCarReservation reservation) {}
}
