package ng.saywich.storageservice.application.contracts.stock.operations;

import ng.saywich.storageservice.domain.stock.StockPartReservation;

public final class UpdateStockPartReservation {
    private UpdateStockPartReservation() {}

    public record Request(StockPartReservation reservation) {}

    public record Response(StockPartReservation reservation) {}
}
