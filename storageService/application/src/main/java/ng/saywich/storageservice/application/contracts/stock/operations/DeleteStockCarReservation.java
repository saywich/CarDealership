package ng.saywich.storageservice.application.contracts.stock.operations;

import ng.saywich.storageservice.domain.stock.StockCarReservation;

import java.util.UUID;

public final class DeleteStockCarReservation {
    private DeleteStockCarReservation() {}

    public record Request(UUID reservationId) {}

    public record Response(StockCarReservation reservation) {}
}
