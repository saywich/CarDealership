package ng.saywich.storageservice.application.contracts.stock.operations;

import ng.saywich.storageservice.domain.stock.StockPartReservation;

import java.util.UUID;

public final class DeleteStockPartReservation {
    private DeleteStockPartReservation() {}

    public record Request(UUID reservationId) {}

    public record Response(StockPartReservation reservation) {}
}
