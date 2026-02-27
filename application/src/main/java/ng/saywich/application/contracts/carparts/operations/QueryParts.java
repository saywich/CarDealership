package ng.saywich.application.contracts.carparts.operations;

import java.util.List;
import java.util.UUID;

public final class QueryParts {
    private QueryParts() {}

    public record Request<T>(
        UUID[] partIds
    ) {}

    public record Response<T>(
        List<T> parts
    ) {}
}
