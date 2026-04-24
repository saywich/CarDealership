package ng.saywich.storageservice.application.contracts.stock;

import ng.saywich.storageservice.application.contracts.stock.operations.CreateStockPartReservation;
import ng.saywich.storageservice.application.contracts.stock.operations.DeleteStockPartReservation;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryStockPartReservationByPartId;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryStockPartReservationByRequestId;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryStockPartReservations;
import ng.saywich.storageservice.application.contracts.stock.operations.UpdateStockPartReservation;

public interface IStockPartReservationService {
    public QueryStockPartReservations.Response query(QueryStockPartReservations.Request request);

    public QueryStockPartReservationByPartId.Response queryByPartId(
        QueryStockPartReservationByPartId.Request request
    );

    public QueryStockPartReservationByRequestId.Response queryByRequestId(
        QueryStockPartReservationByRequestId.Request request
    );

    public CreateStockPartReservation.Response create(CreateStockPartReservation.Request request);

    public UpdateStockPartReservation.Response update(UpdateStockPartReservation.Request request);

    public DeleteStockPartReservation.Response delete(DeleteStockPartReservation.Request request);
}
