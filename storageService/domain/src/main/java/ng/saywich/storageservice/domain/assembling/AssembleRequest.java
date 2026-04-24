package ng.saywich.storageservice.domain.assembling;

import java.util.UUID;

public record AssembleRequest(
    UUID id,
    UUID orderId,
    WarehouseAdmin warehouseAdmin,
    AssembleRequestStatus status
) { }
