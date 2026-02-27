package ng.saywich.application.services.carparts;

import lombok.AllArgsConstructor;
import ng.saywich.application.abstractions.carparts.IPartRepository;
import ng.saywich.application.contracts.carparts.IPartService;
import ng.saywich.application.contracts.carparts.operations.*;
import ng.saywich.domain.car.parts.ICarPart;

@AllArgsConstructor
public class PartService<T extends ICarPart> implements IPartService<T> {
    IPartRepository<T> repository;

    @Override
    public AddPart.Response<T> add(AddPart.Request<T> request) {
        repository.create(request.part());

        return new AddPart.Response<>(request.part());
    }

    @Override
    public QueryParts.Response<T> query(QueryParts.Request<T> request) {
        return new QueryParts.Response<>(repository.query(request.partIds()));
    }

    @Override
    public QueryPartsForModel.Response<T> queryForModel(QueryPartsForModel.Request request) {
        return new QueryPartsForModel.Response<>(repository.queryForModel(request.modelId()));
    }

    @Override
    public UpdatePart.Response<T> update(UpdatePart.Request<T> request) {
        repository.update(request.part());

        return new UpdatePart.Response<>(request.part());
    }

    @Override
    public DeletePart.Response<T> delete(UpdatePart.Request<T> request) {
        repository.delete(request.part().getPartId());

        return new DeletePart.Response<>(request.part());
    }

    @Override
    public LinkToCarModel.Response linkToCarModel(LinkToCarModel.Request request) {
        repository.linkToModel(request.modelId(), request.partId(), request.priceDifference());

        return new LinkToCarModel.Response();
    }
}
