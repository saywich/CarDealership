package ng.saywich.storageservice.application.abstractions.stock;

import ng.saywich.storageservice.domain.stock.StockCarReservation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStockCarReservationRepository {
    List<StockCarReservation> query(UUID[] reservationIds);

    Optional<StockCarReservation> queryById(UUID reservationId);

    Optional<StockCarReservation> queryByCarId(UUID carId);

    Optional<StockCarReservation> queryByRequestId(UUID requestId);

    void create(StockCarReservation reservation);

    void update(StockCarReservation reservation);

    void delete(UUID reservationId);

    boolean isCarAvailable(UUID carId);
}
