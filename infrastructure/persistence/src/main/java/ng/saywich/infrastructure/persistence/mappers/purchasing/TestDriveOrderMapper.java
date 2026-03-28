package ng.saywich.infrastructure.persistence.mappers.purchasing;

import ng.saywich.domain.purchasing.order.TestDriveOrder;
import ng.saywich.infrastructure.persistence.mappers.cars.CarMapper;
import ng.saywich.infrastructure.persistence.models.purchasing.TestDriveOrderDao;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class TestDriveOrderMapper {
    @Autowired
    protected CustomerMapper customerMapper;

    @Autowired
    protected CarMapper carMapper;

    @Mapping(target = "orderId", source = "dao.id")
    @Mapping(target = "customer", expression = "java(customerMapper.toCustomer(dao.getCustomer()))")
    @Mapping(target = "requestedCar", expression = "java(carMapper.toCar(dao.getCar()))")
    @Mapping(target = "startDate", source = "dao.startDate")
    public abstract TestDriveOrder toOrder(TestDriveOrderDao dao);

    @Mapping(target = "id", source = "order.orderId")
    @Mapping(target = "customer", expression = "java(customerMapper.toCustomerDao(order.getCustomer()))")
    @Mapping(target = "car", expression = "java(carMapper.toCarDao(order.getRequestedCar()))")
    @Mapping(target = "startDate", expression = "java(order.getStartDate())")
    public abstract TestDriveOrderDao toOrderDao(TestDriveOrder order);
}
