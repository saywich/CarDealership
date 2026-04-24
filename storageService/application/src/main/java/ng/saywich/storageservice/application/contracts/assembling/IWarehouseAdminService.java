package ng.saywich.storageservice.application.contracts.assembling;

import ng.saywich.storageservice.application.contracts.assembling.operations.CreateWarehouseAdmin;
import ng.saywich.storageservice.application.contracts.assembling.operations.DeleteWarehouseAdmin;
import ng.saywich.storageservice.application.contracts.assembling.operations.QueryWarehouseAdmins;
import ng.saywich.storageservice.application.contracts.assembling.operations.UpdateWarehouseAdmin;

public interface IWarehouseAdminService {
    public QueryWarehouseAdmins.Response query(QueryWarehouseAdmins.Request request);

    public CreateWarehouseAdmin.Response create(CreateWarehouseAdmin.Request request);

    public UpdateWarehouseAdmin.Response update(UpdateWarehouseAdmin.Request request);

    public DeleteWarehouseAdmin.Response delete(DeleteWarehouseAdmin.Request request);
}
