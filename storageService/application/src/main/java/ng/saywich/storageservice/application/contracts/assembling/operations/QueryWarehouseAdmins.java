package ng.saywich.storageservice.application.contracts.assembling.operations;

import ng.saywich.storageservice.domain.assembling.WarehouseAdmin;

import java.util.List;
import java.util.UUID;

public final class QueryWarehouseAdmins {
    private QueryWarehouseAdmins() {}

    public record Request(UUID[] adminIds) {}

    public record Response(List<WarehouseAdmin> admins) {}
}
