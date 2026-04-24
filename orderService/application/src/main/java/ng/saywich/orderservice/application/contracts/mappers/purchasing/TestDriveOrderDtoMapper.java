package ng.saywich.orderservice.application.contracts.mappers.purchasing;

import ng.saywich.orderservice.application.contracts.dtos.purchasing.TestDriveOrderDto;
import ng.saywich.shared.domain.purchasing.order.TestDriveOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class TestDriveOrderDtoMapper {
    @Autowired
    protected CustomerDtoMapper customerMapper;

//    @Autowired
//    protected ICustomerRepository customerRepository;

//    public TestDriveOrder toOrder(TestDriveOrderDto dto) {
//        Optional<Customer> customerOpt
//            = customerRepository.queryById(dto.customerId());
//
//        if (customerOpt.isEmpty()) {
//            throw new DomainValidationException("Can't find such user");
//        }
//
//        return new TestDriveOrder(
//            dto.orderId(),
//            dto.requestedCarId(),
//            customerOpt.get(),
//            dto.startDate()
//        );
//    }

    @Mapping(target = "customer", expression = "java(customerMapper.toCustomerDto(order.getCustomer()))")
    @Mapping(target = "requestedCarId", expression = "java(order.getRequestedCarId())")
    @Mapping(target = "startDate", expression = "java(order.getStartDate())")
    public abstract TestDriveOrderDto toOrderDto(TestDriveOrder order);
}
