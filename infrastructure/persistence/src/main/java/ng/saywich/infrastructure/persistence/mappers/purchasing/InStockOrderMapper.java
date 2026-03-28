package ng.saywich.infrastructure.persistence.mappers.purchasing;

import ng.saywich.domain.purchasing.order.CarInStockOrder;
import ng.saywich.domain.purchasing.order.states.*;
import ng.saywich.infrastructure.persistence.mappers.cars.CarMapper;
import ng.saywich.infrastructure.persistence.models.purchasing.InStockOrderDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class InStockOrderMapper {
    @Autowired
    protected ManagerMapper managerMapper;

    @Autowired
    protected CustomerMapper customerMapper;

    @Autowired
    protected CarMapper carMapper;

    public CarInStockOrder toOrder(InStockOrderDao dao) {
        IOrderState state;

        switch (dao.getOrderState()) {
            case PENDING -> state = new PendingOrderState();
            case ACCEPTED -> state = new AcceptedOrderState();
            case WAITING_FOR_PAYMENT -> state = new WaitingForPaymentOrderState();
            case PAID -> state = new PaidOrderState();
            case WAITING_FOR_DELIVERY -> state = new WaitingForDeliveryOrderState();
            case READY -> state = new ReadyOrderState();
            case COMPLETED -> state = new CompletedOrderState();
            case CANCELLED -> state = new CancelledOrderState();
            default -> throw new IllegalStateException("Unexpected value: " + dao.getOrderState());
        }

        return new CarInStockOrder(
            dao.getId(),
            state,
            managerMapper.toManager(dao.getManager()),
            customerMapper.toCustomer(dao.getCustomer()),
            carMapper.toCar(dao.getCar())
        );
    }

    @Mapping(target = "id", source = "order.orderId")
    @Mapping(target = "orderState", expression = "java(order.getOrderState().getState())")
    @Mapping(target = "customer", expression = "java(customerMapper.toCustomerDao(order.getCustomer()))")
    @Mapping(target = "manager", expression = "java(managerMapper.toManagerDao(order.getManager()))")
    @Mapping(target = "car", expression = "java(carMapper.toCarDao(order.getCar()))")
    public abstract InStockOrderDao toOrderDao(CarInStockOrder order);
}
