package ng.saywich.storageservice.application.services.car;

import lombok.AllArgsConstructor;
import ng.saywich.storageservice.application.contracts.car.operations.QueryCars;
import ng.saywich.storageservice.application.contracts.car.operations.UpdateCar;
import ng.saywich.storageservice.application.contracts.mappers.parts.options.*;
import org.springframework.stereotype.Service;
import ng.saywich.storageservice.application.abstractions.car.ICarRepository;
import ng.saywich.storageservice.application.contracts.car.ICarService;
import ng.saywich.storageservice.application.contracts.car.operations.CreateCar;
import ng.saywich.storageservice.application.contracts.car.operations.DeleteCar;
import ng.saywich.storageservice.application.contracts.mappers.cars.CarBrandDtoMapper;
import ng.saywich.storageservice.application.contracts.mappers.cars.CarDtoMapper;
import ng.saywich.shared.domain.car.Car;
import ng.saywich.shared.domain.car.CarModel;
import ng.saywich.shared.domain.car.parts.OptionSet;
import ng.saywich.shared.domain.value.Price;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CarService implements ICarService {
    private ICarRepository repository;
    private CarDtoMapper mapper;

    private EngineOptionDtoMapper engineDtoMapper;
    private TransmissionOptionDtoMapper transmissionDtoMapper;
    private RimsOptionDtoMapper rimsDtoMapper;
    private InteriorOptionDtoMapper interiorDtoMapper;
    private ColorOptionDtoMapper colorDtoMapper;
    private BodyTypeOptionDtoMapper bodyTypeDtoMapper;
    private CarBrandDtoMapper carBrandDtoMapper;

    @Override
    public CreateCar.Response create(CreateCar.Request request) {
        CarModel.CarModelBuilder builder = CarModel.builder();
        OptionSet.OptionSetBuilder optionSetBuilder = OptionSet.builder();

        builder.modelId(UUID.randomUUID());
        builder.name(request.name());

        optionSetBuilder.optionSetId(UUID.randomUUID());
        optionSetBuilder.engine(engineDtoMapper.toOption(request.engine()));
        optionSetBuilder.transmission(transmissionDtoMapper.toOption(request.transmission()));
        optionSetBuilder.rims(rimsDtoMapper.toOption(request.rims()));
        optionSetBuilder.interior(interiorDtoMapper.toOption(request.interior()));
        optionSetBuilder.bodyType(bodyTypeDtoMapper.toOption(request.bodyType()));
        optionSetBuilder.color(colorDtoMapper.toOption(request.color()));
        builder.optionSet(optionSetBuilder.build());

        builder.brand(carBrandDtoMapper.toCarBrand(request.brand()));
        builder.basePrice(new Price(request.basePrice()));

        CarModel model = builder.build();
        Car car = new Car(UUID.randomUUID(), request.vinNumber(), model);

        repository.createCar(car);

        return new CreateCar.Response(mapper.toCarDto(car));
    }

    @Override
    public UpdateCar.Response update(UpdateCar.Request request) {
        repository.updateCar(mapper.toCar(request.car()));

        return new UpdateCar.Response(request.car());
    }

    @Override
    public DeleteCar.Response delete(DeleteCar.Request request) {
        Car car = repository.query(new UUID[]{ request.carId() }).get(0);
        repository.deleteCar(request.carId());

        return new DeleteCar.Response(mapper.toCarDto(car));
    }

    @Override
    public QueryCars.Response query(QueryCars.Request request) {
        return new QueryCars.Response(repository.query(request.carIds()).stream()
            .map(mapper::toCarDto)
            .toList());
    }

    @Override
    public QueryCars.Response queryPage(QueryCars.Request request, int pageSize, int page) {
        int clampedPageSize = Math.max(1, Math.min(100, pageSize));

        return new QueryCars.Response(
            repository.queryPage(request.carIds(), clampedPageSize, page).stream()
                .map(mapper::toCarDto)
                .toList());
    }

    @Override
    public QueryCars.Response queryInStock() {
        return new QueryCars.Response(repository.queryInStock().stream()
            .map(mapper::toCarDto)
            .toList());
    }
}
