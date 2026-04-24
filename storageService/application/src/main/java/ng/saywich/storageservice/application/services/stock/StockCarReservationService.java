package ng.saywich.storageservice.application.services.stock;

import lombok.AllArgsConstructor;
import ng.saywich.shared.domain.exceptions.DomainValidationException;
import ng.saywich.storageservice.application.abstractions.stock.IStockCarReservationRepository;
import ng.saywich.storageservice.application.contracts.stock.IStockCarReservationService;
import ng.saywich.storageservice.application.contracts.stock.operations.CreateStockCarReservation;
import ng.saywich.storageservice.application.contracts.stock.operations.DeleteStockCarReservation;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryStockCarAvailability;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryStockCarReservationByCarId;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryStockCarReservationByRequestId;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryStockCarReservations;
import ng.saywich.storageservice.application.contracts.stock.operations.UpdateStockCarReservation;
import ng.saywich.storageservice.domain.stock.StockCarReservation;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockCarReservationService implements IStockCarReservationService {
    private IStockCarReservationRepository repository;

    @Override
    public QueryStockCarReservations.Response query(QueryStockCarReservations.Request request) {
        return new QueryStockCarReservations.Response(
            repository.query(request.reservationIds()));
    }

    @Override
    public QueryStockCarReservationByCarId.Response queryByCarId(QueryStockCarReservationByCarId.Request request) {
        return new QueryStockCarReservationByCarId.Response(
            repository.queryByCarId(request.carId()).orElse(null));
    }

    @Override
    public QueryStockCarReservationByRequestId.Response queryByRequestId(
        QueryStockCarReservationByRequestId.Request request
    ) {
        return new QueryStockCarReservationByRequestId.Response(
            repository.queryByRequestId(request.requestId()).orElse(null)
        );
    }

    @Override
    public QueryStockCarAvailability.Response queryAvailability(
        QueryStockCarAvailability.Request request
    ) {
        return new QueryStockCarAvailability.Response(
            repository.isCarAvailable(request.carId()));
    }

    @Override
    public CreateStockCarReservation.Response create(CreateStockCarReservation.Request request) {
        repository.create(request.reservation());

        return new CreateStockCarReservation.Response(request.reservation());
    }

    @Override
    public UpdateStockCarReservation.Response update(
        UpdateStockCarReservation.Request request
    ) {
        repository.update(request.reservation());

        return new UpdateStockCarReservation.Response(request.reservation());
    }

    @Override
    public DeleteStockCarReservation.Response delete(DeleteStockCarReservation.Request request) {
        StockCarReservation reservation =
            repository.queryById(request.reservationId()).orElse(null);

        if (reservation == null) {
            throw new DomainValidationException("Can't find car reservation.");
        }

        repository.delete(request.reservationId());

        return new DeleteStockCarReservation.Response(reservation);
    }
}
