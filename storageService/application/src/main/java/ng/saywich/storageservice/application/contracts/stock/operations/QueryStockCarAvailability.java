package ng.saywich.storageservice.application.contracts.stock.operations;

import java.util.UUID;

public final class QueryStockCarAvailability {
    private QueryStockCarAvailability() {}

    public record Request(UUID carId) {}

    public record Response(Boolean isAvailable) {}
}
