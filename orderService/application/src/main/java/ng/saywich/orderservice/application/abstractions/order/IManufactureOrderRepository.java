package ng.saywich.orderservice.application.abstractions.order;

import ng.saywich.orderservice.application.contracts.order.operations.models.OrderFilter;
import ng.saywich.shared.domain.purchasing.order.CarManufactureOrder;

import java.util.List;
import java.util.UUID;

public interface IManufactureOrderRepository {
    public List<CarManufactureOrder> query(UUID[] orderIds);

    public List<CarManufactureOrder> queryWithFilter(OrderFilter filter);

    public void createOrder(CarManufactureOrder order);

    public void updateOrder(CarManufactureOrder order);
}
