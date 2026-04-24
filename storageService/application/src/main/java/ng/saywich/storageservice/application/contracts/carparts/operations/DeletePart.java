package ng.saywich.storageservice.application.contracts.carparts.operations;

public final class DeletePart {
    private DeletePart() {}

    public record Request<T>(
        T part
    ) {}

    public record Response<T>(
        T part
    ) {}
}
