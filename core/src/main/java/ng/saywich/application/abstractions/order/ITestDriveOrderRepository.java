package ng.saywich.application.abstractions.order;

import ng.saywich.domain.purchasing.order.TestDriveOrder;

import java.util.List;
import java.util.UUID;

public interface ITestDriveOrderRepository {
    public List<TestDriveOrder> query(UUID[] orderIds);

    public void createOrder(TestDriveOrder order);

    public void updateOrder(TestDriveOrder order);
}
