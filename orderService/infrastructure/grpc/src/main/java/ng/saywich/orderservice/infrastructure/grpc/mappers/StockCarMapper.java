package ng.saywich.orderservice.infrastructure.grpc.mappers;

import ng.saywich.orderservice.application.contracts.dtos.carstock.StockCarDto;
import ng.saywich.shared.grpc.carstock.StockCar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StockCarMapper {
//    @Mapping(target = "carId", source = "carId")
//    @Mapping(target = "vinNumber", source = "vinNumber")
//    @Mapping(target = "modelId", source = "modelId")
//    @Mapping(target = "modelName", source = "modelName")
//    @Mapping(target = "brandId", source = "brandId")
//    @Mapping(target = "brandName", source = "brandName")
//    @Mapping(target = "basePrice", source = "basePrice")
    StockCarDto toDto(StockCar car);
}
