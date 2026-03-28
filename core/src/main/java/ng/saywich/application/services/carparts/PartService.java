package ng.saywich.application.services.carparts;

import lombok.AllArgsConstructor;
import ng.saywich.application.abstractions.carparts.IPartOptionRepository;
import ng.saywich.application.abstractions.carparts.IPartRepository;
import ng.saywich.application.contracts.carparts.IPartService;
import ng.saywich.application.contracts.carparts.operations.*;
import ng.saywich.domain.car.parts.ICarPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
