package ng.saywich.storageservice.application.contracts.assembling.operations;

import ng.saywich.storageservice.domain.assembling.WarehouseAdmin;

import java.util.UUID;

public final class DeleteWarehouseAdmin {
    private DeleteWarehouseAdmin() {}

    public record Request(UUID adminId) {}

    public record Response(WarehouseAdmin admin) {}
}
