package ng.saywich.orderservice.application.contracts.mappers.purchasing;

import ng.saywich.orderservice.application.contracts.dtos.purchasing.ManufactureOrderDto;
import ng.saywich.shared.domain.purchasing.order.CarManufactureOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ManufactureOrderDtoMapper {
    @Autowired
    protected ManagerDtoMapper managerMapper;

    @Autowired
    protected CustomerDtoMapper customerMapper;

//    @Autowired
//    protected IManagerRepository managerRepository;
//
//    @Autowired
//    protected ICustomerRepository customerRepository;

//    public CarManufactureOrder toOrder(ManufactureOrderDto dto) {
//        IOrderState state;
//
//        switch (dto.orderState()) {
//            case PENDING -> state = new PendingOrderState();
//            case ACCEPTED -> state = new AcceptedOrderState();
//            case WAITING_FOR_PAYMENT -> state = new WaitingForPaymentOrderState();
//            case PAID -> state = new PaidOrderState();
//            case WAITING_FOR_DELIVERY -> state = new WaitingForDeliveryOrderState();
//            case READY -> state = new ReadyOrderState();
//            case COMPLETED -> state = new CompletedOrderState();
//            case CANCELLED -> state = new CancelledOrderState();
//            default -> throw new IllegalStateException("Unexpected value: " + dto.orderState());
//        }
//
//        return new CarManufactureOrder(
//            dto.orderId(),
//            state,
//            managerMapper.toManager(dto.manager()),
//            customerMapper.toCustomer(dto.customer()),
//            dto.
//        );
//    }

    @Mapping(target = "orderState", expression = "java(order.getOrderState().getState())")
    @Mapping(target = "customer", expression = "java(customerMapper.toCustomerDto(order.getCustomer()))")
    @Mapping(target = "manager", expression = "java(managerMapper.toManagerDto(order.getManager()))")
    @Mapping(target = "carModelId", expression = "java(order.getCarModelId())")
    @Mapping(target = "optionSetId", expression = "java(order.getOptionSetId())")
    public abstract ManufactureOrderDto toOrderDto(CarManufactureOrder order);
}
