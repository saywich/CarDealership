package ng.saywich.application.abstractions.order;

import ng.saywich.domain.purchasing.order.CarInStockOrder;

import java.util.List;
import java.util.UUID;

public interface IInStockOrderRepository {
    public List<CarInStockOrder> query(UUID[] orderIds);

    public void createOrder(CarInStockOrder order);

    public void updateOrder(CarInStockOrder order);
}
