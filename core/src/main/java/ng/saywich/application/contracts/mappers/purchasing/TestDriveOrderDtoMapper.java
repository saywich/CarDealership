package ng.saywich.application.contracts.mappers.purchasing;

import ng.saywich.domain.purchasing.order.TestDriveOrder;
import ng.saywich.application.contracts.dtos.purchasing.TestDriveOrderDto;
import ng.saywich.application.contracts.mappers.cars.CarDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class TestDriveOrderDtoMapper {
    @Autowired
    protected CustomerDtoMapper customerMapper;

    @Autowired
    protected CarDtoMapper carMapper;

    @Mapping(target = "customer", expression = "java(customerMapper.toCustomer(dto.customer()))")
    @Mapping(target = "requestedCar", expression = "java(carMapper.toCar(dto.requestedCar()))")
    @Mapping(target = "startDate", source = "dto.startDate")
    public abstract TestDriveOrder toOrder(TestDriveOrderDto dto);

    @Mapping(target = "customer", expression = "java(customerMapper.toCustomerDto(order.getCustomer()))")
    @Mapping(target = "requestedCar", expression = "java(carMapper.toCarDto(order.getRequestedCar()))")
    @Mapping(target = "startDate", expression = "java(order.getStartDate())")
    public abstract TestDriveOrderDto toOrderDto(TestDriveOrder order);
}
