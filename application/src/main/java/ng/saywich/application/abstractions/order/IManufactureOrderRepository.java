package ng.saywich.application.abstractions.order;

import ng.saywich.domain.purchasing.order.CarManufactureOrder;

import java.util.List;
import java.util.UUID;

public interface IManufactureOrderRepository {
    public List<CarManufactureOrder> query(UUID[] orderIds);

    public void createOrder(CarManufactureOrder order);

    public void updateOrder(CarManufactureOrder order);
}
