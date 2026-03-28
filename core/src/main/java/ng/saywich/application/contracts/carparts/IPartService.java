package ng.saywich.application.contracts.carparts;

import ng.saywich.application.contracts.carparts.operations.*;
import ng.saywich.domain.car.parts.ICarPart;

public interface IPartService<T extends ICarPart> {
    public AddPart.Response<T> add(AddPart.Request<T> request);

    public QueryParts.Response<T> query(QueryParts.Request<T> request);

    public UpdatePart.Response<T> update(UpdatePart.Request<T> request);

    public DeletePart.Response<T> delete(DeletePart.Request<T> request);
}
