package ng.saywich.orderservice.infrastructure.persistence.mappers.purchasing;

import ng.saywich.shared.domain.purchasing.order.TestDriveOrder;
import ng.saywich.orderservice.infrastructure.persistence.models.purchasing.TestDriveOrderDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class TestDriveOrderMapper {
    @Autowired
    protected CustomerMapper customerMapper;

    @Mapping(target = "orderId", source = "dao.id")
    @Mapping(target = "customer", expression = "java(customerMapper.toCustomer(dao.getCustomer()))")
    @Mapping(target = "requestedCarId", expression = "java(dao.getCarId())")
    @Mapping(target = "startDate", source = "dao.startDate")
    public abstract TestDriveOrder toOrder(TestDriveOrderDao dao);

    @Mapping(target = "id", source = "order.orderId")
    @Mapping(target = "customer", expression = "java(customerMapper.toCustomerDao(order.getCustomer()))")
    @Mapping(target = "carId", expression = "java(order.getRequestedCarId())")
    @Mapping(target = "startDate", expression = "java(order.getStartDate())")
    public abstract TestDriveOrderDao toOrderDao(TestDriveOrder order);
}
