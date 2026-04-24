package ng.saywich.storageservice.application.services.stock;

import lombok.AllArgsConstructor;
import ng.saywich.shared.domain.exceptions.DomainValidationException;
import ng.saywich.storageservice.application.abstractions.stock.IStockPartReservationRepository;
import ng.saywich.storageservice.application.contracts.stock.IStockPartReservationService;
import ng.saywich.storageservice.application.contracts.stock.operations.CreateStockPartReservation;
import ng.saywich.storageservice.application.contracts.stock.operations.DeleteStockPartReservation;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryStockPartReservationByPartId;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryStockPartReservationByRequestId;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryStockPartReservations;
import ng.saywich.storageservice.application.contracts.stock.operations.UpdateStockPartReservation;
import ng.saywich.storageservice.domain.stock.StockPartReservation;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StockPartReservationService implements IStockPartReservationService {
    private IStockPartReservationRepository repository;

    @Override
    public QueryStockPartReservations.Response query(
        QueryStockPartReservations.Request request
    ) {
        return new QueryStockPartReservations.Response(
            repository.query(request.reservationIds()));
    }

    @Override
    public QueryStockPartReservationByPartId.Response queryByPartId(
        QueryStockPartReservationByPartId.Request request
    ) {
        return new QueryStockPartReservationByPartId.Response(
            repository.queryByPartId(request.partId()));
    }

    @Override
    public QueryStockPartReservationByRequestId.Response queryByRequestId(
        QueryStockPartReservationByRequestId.Request request
    ) {
        return new QueryStockPartReservationByRequestId.Response(
            repository.queryByRequestId(request.requestId()));
    }

    @Override
    public CreateStockPartReservation.Response create(
        CreateStockPartReservation.Request request
    ) {
        repository.create(request.reservation());

        return new CreateStockPartReservation.Response(request.reservation());
    }

    @Override
    public UpdateStockPartReservation.Response update(
        UpdateStockPartReservation.Request request
    ) {
        repository.update(request.reservation());

        return new UpdateStockPartReservation.Response(request.reservation());
    }

    @Override
    public DeleteStockPartReservation.Response delete(DeleteStockPartReservation.Request request) {
        StockPartReservation reservation =
            repository.queryById(request.reservationId()).orElse(null);

        if (reservation == null) {
            throw new DomainValidationException("Can't find part reservation.");
        }

        repository.delete(request.reservationId());

        return new DeleteStockPartReservation.Response(reservation);
    }
}
