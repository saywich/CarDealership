package ng.saywich.application.abstractions.order;

import ng.saywich.domain.purchasing.order.TestDriveOrder;

import java.util.UUID;

public interface ITestDriveOrderRepository {
    public TestDriveOrder query(UUID[] orderIds);

    public void createOrder(TestDriveOrder order);

    public void updateOrder(TestDriveOrder order);
}
