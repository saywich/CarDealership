package ng.saywich.application.services.car;

import lombok.AllArgsConstructor;
import ng.saywich.application.abstractions.car.ICarRepository;
import ng.saywich.application.contracts.car.ICarService;
import ng.saywich.application.contracts.car.operations.*;
import ng.saywich.domain.car.Car;
import ng.saywich.domain.car.CarModel;

import java.util.UUID;

@AllArgsConstructor
public class CarService implements ICarService {
    private ICarRepository repository;

    @Override
    public CreateCar.Response create(CreateCar.Request request) {
        CarModel.CarModelBuilder builder = CarModel.builder();

        builder.modelId(UUID.randomUUID());
        builder.name(request.name());
        builder.engine(request.engine());
        builder.transmission(request.transmission());
        builder.rims(request.rims());
        builder.interior(request.interior());
        builder.color(request.color());
        builder.brand(request.brand());
        builder.basePrice(request.basePrice());

        CarModel model = builder.build();
        Car car = new Car(UUID.randomUUID(), request.vinNumber(), model);

        repository.createCar(car);

        return new CreateCar.Response(car);
    }

    @Override
    public UpdateCar.Response update(UpdateCar.Request request) {
        repository.updateCar(request.car());
        return new UpdateCar.Response();
    }

    @Override
    public DeleteCar.Response delete(DeleteCar.Request request) {
        repository.deleteCar(request.carId());
        return new DeleteCar.Response();
    }

    @Override
    public QueryCars.Response query(QueryCars.Request request) {
        return new QueryCars.Response(repository.query(request.carIds()));
    }
}
