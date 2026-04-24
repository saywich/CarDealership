package ng.saywich.storageservice.application.contracts.mappers.cars;

import ng.saywich.storageservice.application.contracts.car.operations.models.CarModelFilter;
import ng.saywich.storageservice.application.contracts.dtos.cars.CarModelFilterDto;
import ng.saywich.storageservice.application.value.CarFullName;
import ng.saywich.storageservice.application.value.Range;
import ng.saywich.shared.domain.value.HorsePower;
import ng.saywich.shared.domain.value.Price;
import ng.saywich.shared.domain.value.Volume;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.math.BigDecimal;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CarModelFilterDtoMapper {
    @Mapping(target = "priceRange", expression = "java(mapPriceRange(dto.minPrice(), dto.maxPrice()))")
    @Mapping(target = "carFullName", expression = "java(mapCarFullName(dto.brandName(), dto.modelName()))")
    @Mapping(target = "powerRange", expression = "java(mapPowerRange(dto.minPower(), dto.maxPower()))")
    @Mapping(target = "volumeRange", expression = "java(mapVolumeRange(dto.minVolume(), dto.maxVolume()))")
    public abstract CarModelFilter toFilter(CarModelFilterDto dto);

    protected Range<Price> mapPriceRange(BigDecimal min, BigDecimal max) {
        if (min == null && max == null) return null;

        return new Range<>(
            min != null ? new Price(min) : null,
            max != null ? new Price(max) : null
        );
    }

    protected Range<HorsePower> mapPowerRange(Integer min, Integer max) {
        int minimum = 0;
        int maximum = Integer.MAX_VALUE;

        if (min != null) minimum = min;
        if (max != null) maximum = max;

        return new Range<>(
            new HorsePower(minimum),
            new HorsePower(maximum)
        );
    }

    protected Range<Volume> mapVolumeRange(Double min, Double max) {
        double minimum = 0;
        double maximum = Double.MAX_VALUE;

        if (min != null) minimum = min;
        if (max != null) maximum = max;

        return new Range<>(
            new Volume(minimum),
            new Volume(maximum)
        );
    }

    protected CarFullName mapCarFullName(String brand, String model) {
        if (brand == null) return null;
        if (model == null) return new CarFullName(brand, Optional.empty());

        return new CarFullName(brand, Optional.of(model));
    }
}
