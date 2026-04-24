package ng.saywich.storageservice.application.services.car;

import lombok.AllArgsConstructor;
import ng.saywich.storageservice.application.abstractions.car.ICarBrandRepository;
import ng.saywich.storageservice.application.abstractions.car.ICarModelRepository;
import ng.saywich.storageservice.application.abstractions.carparts.IOptionSetRepository;
import ng.saywich.storageservice.application.abstractions.carparts.IPartRepository;
import ng.saywich.storageservice.application.contracts.car.ICarModelService;
import ng.saywich.storageservice.application.contracts.car.operations.*;
import ng.saywich.storageservice.application.contracts.mappers.cars.CarModelDtoMapper;
import ng.saywich.storageservice.application.contracts.mappers.cars.CarModelFilterDtoMapper;
import ng.saywich.storageservice.application.contracts.mappers.cars.OptionSetDtoMapper;
import ng.saywich.shared.domain.car.AvailableOptions;
import ng.saywich.shared.domain.car.CarModel;
import ng.saywich.shared.domain.car.CarModelConfigurer;
import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.parts.*;
import ng.saywich.shared.domain.value.Price;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarModelService implements ICarModelService {
    private ICarModelRepository repository;
    private ICarBrandRepository brandRepository;
    private IOptionSetRepository optionSetRepository;
    private IPartRepository<Engine> engineRepository;
    private IPartRepository<Transmission> transmissionRepository;
    private IPartRepository<Rims> rimsRepository;
    private IPartRepository<Interior> interiorRepository;
    private IPartRepository<Color> colorRepository;

    private CarModelDtoMapper carModelMapper;
    private CarModelFilterDtoMapper filterMapper;
    private OptionSetDtoMapper optionSetMapper;

    @Override
    public CreateCarModel.Response create(CreateCarModel.Request request) {
        CarModel.CarModelBuilder builder = CarModel.builder();
        OptionSet.OptionSetBuilder optionSetBuilder = OptionSet.builder();

        builder.modelId(UUID.randomUUID());
        builder.name(request.name());

        optionSetBuilder.optionSetId(UUID.randomUUID());
        optionSetBuilder.engine(
            CarOption.zero(
                UUID.randomUUID(),
                engineRepository.query(new UUID[] { request.engineId() }).get(0)
            )
        );
        optionSetBuilder.transmission(
            CarOption.zero(
                UUID.randomUUID(),
                transmissionRepository.query(new UUID[] { request.transmissionId() }).get(0)
            )
        );
        optionSetBuilder.rims(CarOption.zero(
            UUID.randomUUID(),
            rimsRepository.query(new UUID[] { request.rimsId() }).get(0)
        ));
        optionSetBuilder.interior(CarOption.zero(
            UUID.randomUUID(),
            interiorRepository.query(new UUID[] { request.interiorId() }).get(0)
        ));
        optionSetBuilder.bodyType(CarOption.zero(
            UUID.randomUUID(),
            request.bodyType()
        ));
        optionSetBuilder.color(CarOption.zero(
            UUID.randomUUID(),
            colorRepository.query(new UUID[] { request.colorId() }).get(0)
        ));
        OptionSet optionSet = optionSetBuilder.build();

        optionSetRepository.create(optionSet);

        builder.optionSet(optionSet);

        builder.brand(brandRepository.query(new UUID[] { request.brandId() }).get(0));
        builder.basePrice(new Price(request.basePrice()));

        CarModel model = builder.build();

        repository.createCarModel(model);

        return new CreateCarModel.Response(carModelMapper.toCarModelDto(model));
    }

    @Override
    public UpdateCarModel.Response update(UpdateCarModel.Request request) {
        repository.updateCarModel(carModelMapper.toCarModel(request.model()));

        return new UpdateCarModel.Response(request.model());
    }

    @Override
    public DeleteCarModel.Response delete(DeleteCarModel.Request request) {
        CarModel model = repository.query(new UUID[]{ request.modelId() }).get(0);

        repository.deleteCarModel(request.modelId());

        return new DeleteCarModel.Response(carModelMapper.toCarModelDto(model));
    }

    @Override
    public GetCarModelDetails.Response getDetails(GetCarModelDetails.Request request) {
        CarModel model = repository.query(new UUID[]{ request.modelId() }).get(0);
        AvailableOptions options = repository.getAvailableOptionsForModel(model.getModelId());

        return new GetCarModelDetails.Response(carModelMapper.toCarModelDto(model), options);
    }

    @Override
    public QueryCarModels.Response query(QueryCarModels.Request request) {
        return new QueryCarModels.Response(
            repository.query(request.modelIds()).stream()
                .map(carModelMapper::toCarModelDto)
                .toList());
    }

    @Override
    public QueryCarModels.Response queryPage(QueryCarModels.Request request, int pageSize, int page) {
        int clampedPageSize = Math.max(1, Math.min(100, pageSize));

        return new QueryCarModels.Response(
            repository.queryPage(new UUID[0], clampedPageSize, page).stream()
                .map(carModelMapper::toCarModelDto)
                .toList());
    }

    @Override
    public FilterCarModels.Response queryWithFilter(
        FilterCarModels.Request request,
        int pageSize,
        int page
    ) {
        List<CarModel> filteredModels =
            repository.queryPageWithFilter(filterMapper.toFilter(request.filter()), pageSize, page);

        return new FilterCarModels.Response(filteredModels.stream()
            .map(carModelMapper::toCarModelDto)
            .toList());
    }

    @Override
    public ConfigureCarModel.Response configure(ConfigureCarModel.Request request) {
        AvailableOptions options = repository.getAvailableOptionsForModel(
            request.model().modelId());

        CarModelConfigurer configurer = new CarModelConfigurer(
            options,
            optionSetMapper.toOptionSet(request.model().optionSet()).toBuilder()
        );

        configurer.setEngine(request.configuration().engine());
        configurer.setTransmission(request.configuration().transmission());
        configurer.setRims(request.configuration().rims());
        configurer.setInterior(request.configuration().interior());
        configurer.setColor(request.configuration().color());
        configurer.setBodyType(request.configuration().bodyType());

        return new ConfigureCarModel.Response(
            carModelMapper.toCarModelDto(carModelMapper.toCarModel(request.model())
                .toBuilder()
                .optionSet(configurer.configure())
                .build())
        );
    }
}
