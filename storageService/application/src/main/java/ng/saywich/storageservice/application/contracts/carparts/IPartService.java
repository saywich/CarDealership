package ng.saywich.storageservice.application.contracts.carparts;

import ng.saywich.storageservice.application.contracts.carparts.operations.AddPart;
import ng.saywich.storageservice.application.contracts.carparts.operations.DeletePart;
import ng.saywich.storageservice.application.contracts.carparts.operations.QueryParts;
import ng.saywich.storageservice.application.contracts.carparts.operations.UpdatePart;
import ng.saywich.shared.domain.car.parts.ICarPart;

public interface IPartService<T extends ICarPart> {
    public AddPart.Response<T> add(AddPart.Request<T> request);

    public QueryParts.Response<T> query(QueryParts.Request<T> request);

    public UpdatePart.Response<T> update(UpdatePart.Request<T> request);

    public DeletePart.Response<T> delete(DeletePart.Request<T> request);
}
