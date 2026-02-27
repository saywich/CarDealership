package ng.saywich.application.contracts.order;

import ng.saywich.application.contracts.order.operations.CreateTestDriveOrder;

public interface ITestDriveOrderService {
    CreateTestDriveOrder.Response create(CreateTestDriveOrder.Request request);
}
