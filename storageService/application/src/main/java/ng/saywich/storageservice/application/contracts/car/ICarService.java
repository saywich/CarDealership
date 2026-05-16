package ng.saywich.storageservice.application.contracts.car;

import ng.saywich.storageservice.application.contracts.car.operations.CreateCar;
import ng.saywich.storageservice.application.contracts.car.operations.DeleteCar;
import ng.saywich.storageservice.application.contracts.car.operations.QueryCars;
import ng.saywich.storageservice.application.contracts.car.operations.UpdateCar;

public interface ICarService {
    public CreateCar.Response create(CreateCar.Request request);

    public UpdateCar.Response update(UpdateCar.Request request);

    public DeleteCar.Response delete(DeleteCar.Request request);

    public QueryCars.Response query(QueryCars.Request request);

    public QueryCars.Response queryPage(QueryCars.Request request, int pageSize, int page);

    public QueryCars.Response queryInStock();
}
