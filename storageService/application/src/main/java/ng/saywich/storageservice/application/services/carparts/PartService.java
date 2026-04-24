package ng.saywich.storageservice.application.services.carparts;

import lombok.AllArgsConstructor;
import ng.saywich.storageservice.application.abstractions.carparts.IPartRepository;
import ng.saywich.storageservice.application.contracts.carparts.IPartService;
import ng.saywich.storageservice.application.contracts.carparts.operations.AddPart;
import ng.saywich.storageservice.application.contracts.carparts.operations.DeletePart;
import ng.saywich.storageservice.application.contracts.carparts.operations.QueryParts;
import ng.saywich.storageservice.application.contracts.carparts.operations.UpdatePart;
import ng.saywich.shared.domain.car.parts.ICarPart;

@AllArgsConstructor
public abstract class PartService<T extends ICarPart> implements IPartService<T> {
    protected abstract IPartRepository<T> getRepository();

    @Override
    public AddPart.Response<T> add(AddPart.Request<T> request) {
        getRepository().create(request.part());

        return new AddPart.Response<>(request.part());
    }

    @Override
    public QueryParts.Response<T> query(QueryParts.Request<T> request) {
        return new QueryParts.Response<>(getRepository().query(request.partIds()));
    }

    @Override
    public UpdatePart.Response<T> update(UpdatePart.Request<T> request) {
        getRepository().update(request.part());

        return new UpdatePart.Response<>(request.part());
    }

    @Override
    public DeletePart.Response<T> delete(DeletePart.Request<T> request) {
        getRepository().delete(request.part());

        return new DeletePart.Response<>(request.part());
    }
}
