package ng.saywich.application.contracts.carparts.operations;

import java.util.List;
import java.util.UUID;

public final class QueryPartsForModel {
    private QueryPartsForModel() {}

    public record Request(
        UUID modelId
    ) {}

    public record Response<T>(
        List<T> parts
    ) {}
}