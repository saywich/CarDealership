package ng.saywich.storageservice.application.contracts.stock;

import ng.saywich.storageservice.application.contracts.stock.operations.CreateStockCarReservation;
import ng.saywich.storageservice.application.contracts.stock.operations.DeleteStockCarReservation;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryStockCarAvailability;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryStockCarReservationByCarId;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryStockCarReservationByRequestId;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryStockCarReservations;
import ng.saywich.storageservice.application.contracts.stock.operations.UpdateStockCarReservation;

public interface IStockCarReservationService {
    public QueryStockCarReservations.Response query(QueryStockCarReservations.Request request);

    public QueryStockCarReservationByCarId.Response queryByCarId(QueryStockCarReservationByCarId.Request request);

    public QueryStockCarReservationByRequestId.Response queryByRequestId(
        QueryStockCarReservationByRequestId.Request request
    );

    public QueryStockCarAvailability.Response queryAvailability(QueryStockCarAvailability.Request request);

    public CreateStockCarReservation.Response create(CreateStockCarReservation.Request request);

    public UpdateStockCarReservation.Response update(UpdateStockCarReservation.Request request);

    public DeleteStockCarReservation.Response delete(DeleteStockCarReservation.Request request);
}
