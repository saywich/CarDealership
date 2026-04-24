package ng.saywich.orderservice.infrastructure.persistence.mappers.purchasing;

import ng.saywich.shared.domain.purchasing.order.CarManufactureOrder;
import ng.saywich.orderservice.infrastructure.persistence.models.purchasing.ManufactureOrderDao;
import ng.saywich.shared.domain.purchasing.order.states.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ManufactureOrderMapper {
    @Autowired
    protected ManagerMapper managerMapper;

    @Autowired
    protected CustomerMapper customerMapper;

    public CarManufactureOrder toOrder(ManufactureOrderDao dao) {
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

        return new CarManufactureOrder(
            dao.getId(),
            state,
            managerMapper.toManager(dao.getManager()),
            customerMapper.toCustomer(dao.getCustomer()),
            dao.getCarModelId(),
            dao.getOptionSetId()
        );
    }

    @Mapping(target = "id", source = "order.orderId")
    @Mapping(target = "orderState", expression = "java(order.getOrderState().getState())")
    @Mapping(target = "customer", expression = "java(customerMapper.toCustomerDao(order.getCustomer()))")
    @Mapping(target = "manager", expression = "java(managerMapper.toManagerDao(order.getManager()))")
    @Mapping(target = "carModelId", expression = "java(order.getCarModelId())")
    @Mapping(target = "optionSetId", expression = "java(order.getOptionSetId())")
    public abstract ManufactureOrderDao toOrderDao(CarManufactureOrder order);
}
