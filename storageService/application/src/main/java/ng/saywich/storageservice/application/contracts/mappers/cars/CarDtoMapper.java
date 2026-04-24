package ng.saywich.storageservice.application.contracts.mappers.cars;

import ng.saywich.storageservice.application.contracts.dtos.cars.CarDto;
import ng.saywich.storageservice.application.contracts.mappers.cars.CarModelDtoMapper;
import ng.saywich.shared.domain.car.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CarDtoMapper {
    @Autowired
    protected CarModelDtoMapper carModelDtoMapper;

    @Mapping(target = "model", expression = "java(carModelDtoMapper.toCarModel(dto.model()))")
    public abstract Car toCar(CarDto dto);

    @Mapping(target = "model", expression = "java(carModelDtoMapper.toCarModelDto(car.model()))")
    public abstract CarDto toCarDto(Car car);
}
