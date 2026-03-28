package ng.saywich.presentation.mappers.purchasing;

import ng.saywich.domain.purchasing.order.CarInStockOrder;
import ng.saywich.domain.purchasing.order.states.*;
import ng.saywich.infrastructure.persistence.models.purchasing.InStockOrderDao;
import ng.saywich.presentation.dtos.purchasing.InStockOrderDto;
import ng.saywich.presentation.mappers.cars.CarDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class InStockOrderDtoMapper {
    @Autowired
    protected ManagerDtoMapper managerMapper;

    @Autowired
    protected CustomerDtoMapper customerMapper;

    @Autowired
    protected CarDtoMapper carMapper;

    public CarInStockOrder toOrder(InStockOrderDto dto) {
        IOrderState state;

        switch (dto.orderState()) {
            case PENDING -> state = new PendingOrderState();
            case ACCEPTED -> state = new AcceptedOrderState();
            case WAITING_FOR_PAYMENT -> state = new WaitingForPaymentOrderState();
            case PAID -> state = new PaidOrderState();
            case WAITING_FOR_DELIVERY -> state = new WaitingForDeliveryOrderState();
            case READY -> state = new ReadyOrderState();
            case COMPLETED -> state = new CompletedOrderState();
            case CANCELLED -> state = new CancelledOrderState();
            default -> throw new IllegalStateException("Unexpected value: " + dto.orderState());
        }

        return new CarInStockOrder(
            dto.orderId(),
            state,
            managerMapper.toManager(dto.manager()),
            customerMapper.toCustomer(dto.customer()),
            carMapper.toCar(dto.car())
        );
    }

    @Mapping(target = "orderState", expression = "java(order.getOrderState().getState())")
    @Mapping(target = "customer", expression = "java(customerMapper.toCustomerDto(order.getCustomer()))")
    @Mapping(target = "manager", expression = "java(managerMapper.toManagerDto(order.getManager()))")
    @Mapping(target = "car", expression = "java(carMapper.toCarDto(order.getCar()))")
    public abstract InStockOrderDto toOrderDto(CarInStockOrder order);
}
