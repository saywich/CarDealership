package ng.saywich.orderservice.application.contracts.order;

import ng.saywich.orderservice.application.contracts.order.operations.CreateTestDriveOrder;

public interface ITestDriveOrderService {
    CreateTestDriveOrder.Response create(CreateTestDriveOrder.Request request);
}
