package ng.saywich.application.contracts.car;

import ng.saywich.application.contracts.car.operations.*;

public interface ICarService {
    public CreateCar.Response create(CreateCar.Request request);

    public UpdateCar.Response update(UpdateCar.Request request);

    public DeleteCar.Response delete(DeleteCar.Request request);

    public QueryCars.Response query(QueryCars.Request request);

    public QueryCars.Response queryPage(QueryCars.Request request, int pageSize, int page);
}
