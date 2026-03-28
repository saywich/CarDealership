package ng.saywich.application.services.car;

import lombok.AllArgsConstructor;
import ng.saywich.application.abstractions.car.ICarRepository;
import ng.saywich.application.contracts.car.ICarService;
import ng.saywich.application.contracts.car.operations.*;
import ng.saywich.domain.car.Car;
import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.car.parts.OptionSet;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CarService implements ICarService {
    private ICarRepository repository;

    @Override
    public CreateCar.Response create(CreateCar.Request request) {
        CarModel.CarModelBuilder builder = CarModel.builder();
        OptionSet.OptionSetBuilder optionSetBuilder = OptionSet.builder();

        builder.modelId(UUID.randomUUID());
        builder.name(request.name());

        optionSetBuilder.optionSetId(UUID.randomUUID());
        optionSetBuilder.engine(request.engine());
        optionSetBuilder.transmission(request.transmission());
        optionSetBuilder.rims(request.rims());
        optionSetBuilder.interior(request.interior());
        optionSetBuilder.bodyType(request.bodyType());
        optionSetBuilder.color(request.color());
        builder.optionSet(optionSetBuilder.build());

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

        return new UpdateCar.Response(request.car());
    }

    @Override
    public DeleteCar.Response delete(DeleteCar.Request request) {
        Car car = repository.query(new UUID[]{ request.carId() }).get(0);
        repository.deleteCar(request.carId());

        return new DeleteCar.Response(car);
    }

    @Override
    public QueryCars.Response query(QueryCars.Request request) {
        return new QueryCars.Response(repository.query(request.carIds()));
    }
}
