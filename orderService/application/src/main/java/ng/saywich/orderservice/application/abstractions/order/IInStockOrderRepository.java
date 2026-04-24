package ng.saywich.orderservice.application.abstractions.order;


import ng.saywich.orderservice.application.contracts.order.operations.models.OrderFilter;
import ng.saywich.shared.domain.purchasing.order.CarInStockOrder;

import java.util.List;
import java.util.UUID;

public interface IInStockOrderRepository {
    public List<CarInStockOrder> query(UUID[] orderIds);

    public List<CarInStockOrder> queryWithFilter(OrderFilter filter);

    public void createOrder(CarInStockOrder order);

    public void updateOrder(CarInStockOrder order);
}
