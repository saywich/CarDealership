package ng.saywich.storageservice.application.contracts.stock.operations;

import ng.saywich.storageservice.domain.stock.StockCarReservation;

public final class CreateStockCarReservation {
    private CreateStockCarReservation() {}

    public record Request(StockCarReservation reservation) {}

    public record Response(StockCarReservation reservation) {}
}
