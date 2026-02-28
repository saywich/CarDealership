package ng.saywich.application.services.car;

import lombok.AllArgsConstructor;
import ng.saywich.application.abstractions.car.ICarModelRepository;
import ng.saywich.application.contracts.car.ICarModelService;
import ng.saywich.application.contracts.car.operations.*;
import ng.saywich.domain.car.AvailableOptions;
import ng.saywich.domain.car.Car;
import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.car.CarModelConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class CarModelService implements ICarModelService {
    private ICarModelRepository repository;

    @Override
    public CreateCarModel.Response create(CreateCarModel.Request request) {
        CarModel.CarModelBuilder builder = CarModel.builder();

        builder.modelId(UUID.randomUUID());
        builder.name(request.name());
        builder.engine(request.engine());
        builder.transmission(request.transmission());
        builder.rims(request.rims());
        builder.interior(request.interior());
        builder.bodyType(request.bodyType());
        builder.color(request.color());
        builder.brand(request.brand());
        builder.basePrice(request.basePrice());

        CarModel model = builder.build();

        repository.createCarModel(model);

        return new CreateCarModel.Response(model);
    }

    @Override
    public UpdateCarModel.Response update(UpdateCarModel.Request request) {
        repository.updateCarModel(request.model());

        return new UpdateCarModel.Response();
    }

    @Override
    public DeleteCarModel.Response delete(DeleteCarModel.Request request) {
        repository.deleteCarModel(request.modelId());

        return new DeleteCarModel.Response();
    }

    @Override
    public GetCarModelDetails.Response getDetails(GetCarModelDetails.Request request) {
        CarModel model = repository.query(new UUID[]{ request.modelId() }).get(0);
        AvailableOptions options = repository.getAvailableOptionsForModel(model.getModelId());

        return new GetCarModelDetails.Response(model, options);
    }

    @Override
    public QueryCarModels.Response query(QueryCarModels.Request request) {
        return new QueryCarModels.Response(repository.query(request.modelIds()));
    }

    @Override
    public FilterCarModels.Response queryWithFilter(FilterCarModels.Request request) {
        List<CarModel> filteredModels = request.criteria()
            .meetCriteria(repository.query(new UUID[]{}));

        return new FilterCarModels.Response(filteredModels);
    }

    @Override
    public ConfigureCarModel.Response configure(ConfigureCarModel.Request request) {
        AvailableOptions options = repository.getAvailableOptionsForModel(
            request.model().getModelId());

        CarModelConfigurer configurer = new CarModelConfigurer(
            options,
            request.model().toBuilder()
        );

        configurer.setEngine(request.configuration().engine());
        configurer.setTransmission(request.configuration().transmission());
        configurer.setRims(request.configuration().rims());
        configurer.setInterior(request.configuration().interior());
        configurer.setColor(request.configuration().color());
        configurer.setBodyType(request.configuration().bodyType());

        return new ConfigureCarModel.Response(configurer.configure());
    }
}
