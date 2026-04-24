package ng.saywich.storageservice.application.contracts.stock;

import ng.saywich.storageservice.application.contracts.stock.operations.CreatePartStockUnit;
import ng.saywich.storageservice.application.contracts.stock.operations.DeletePartStockUnit;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryPartStockAvailability;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryPartStockUnitByPartId;
import ng.saywich.storageservice.application.contracts.stock.operations.QueryPartStockUnits;
import ng.saywich.storageservice.application.contracts.stock.operations.UpdatePartStockUnit;

public interface IPartStockUnitService {
    public QueryPartStockUnits.Response query(QueryPartStockUnits.Request request);

    public QueryPartStockUnitByPartId.Response queryByPartId(QueryPartStockUnitByPartId.Request request);

    public QueryPartStockAvailability.Response queryAvailability(QueryPartStockAvailability.Request request);

    public CreatePartStockUnit.Response create(CreatePartStockUnit.Request request);

    public UpdatePartStockUnit.Response update(UpdatePartStockUnit.Request request);

    public DeletePartStockUnit.Response delete(DeletePartStockUnit.Request request);
}
