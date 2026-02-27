package ng.saywich.application.contracts.carparts.operations;

public final class UpdatePart {
    private UpdatePart() {}

    public record Request<T>(
        T part
    ) {}

    public record Response<T>(
        T part
    ) {}
}

