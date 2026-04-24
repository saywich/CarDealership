package ng.saywich.storageservice.application.contracts.stock.operations;

import ng.saywich.storageservice.domain.stock.StockPartReservation;

public final class CreateStockPartReservation {
    private CreateStockPartReservation() {}

    public record Request(StockPartReservation reservation) {}

    public record Response(StockPartReservation reservation) {}
}
