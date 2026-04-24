package ng.saywich.storageservice.application.abstractions.stock;

import ng.saywich.storageservice.domain.stock.StockPartReservation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStockPartReservationRepository {
    List<StockPartReservation> query(UUID[] reservationIds);

    Optional<StockPartReservation> queryById(UUID reservationId);

    List<StockPartReservation> queryByPartId(UUID partId);

    List<StockPartReservation> queryByRequestId(UUID requestId);

    void create(StockPartReservation reservation);

    void update(StockPartReservation reservation);

    void delete(UUID reservationId);
}
