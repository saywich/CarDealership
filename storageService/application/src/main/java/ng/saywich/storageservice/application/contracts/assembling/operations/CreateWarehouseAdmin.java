package ng.saywich.storageservice.application.contracts.assembling.operations;

import ng.saywich.storageservice.domain.assembling.WarehouseAdmin;

public final class CreateWarehouseAdmin {
    private CreateWarehouseAdmin() {}

    public record Request(WarehouseAdmin admin) {}

    public record Response(WarehouseAdmin admin) {}
}
