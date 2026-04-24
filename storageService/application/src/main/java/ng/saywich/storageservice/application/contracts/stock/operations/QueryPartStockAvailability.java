package ng.saywich.storageservice.application.contracts.stock.operations;

import java.util.UUID;

public final class QueryPartStockAvailability {
    private QueryPartStockAvailability() {}

    public record Request(UUID partId) {}

    public record Response(Boolean isAvailable) {}
}
