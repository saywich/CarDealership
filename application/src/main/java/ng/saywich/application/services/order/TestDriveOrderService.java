package ng.saywich.application.services.order;

import lombok.AllArgsConstructor;
import ng.saywich.application.abstractions.order.ITestDriveOrderRepository;
import ng.saywich.application.contracts.order.ITestDriveOrderService;
import ng.saywich.application.contracts.order.operations.CreateTestDriveOrder;
import ng.saywich.domain.purchasing.order.TestDriveOrder;

import java.util.UUID;

@AllArgsConstructor
public class TestDriveOrderService implements ITestDriveOrderService {
    ITestDriveOrderRepository repository;

    @Override
    public CreateTestDriveOrder.Response create(CreateTestDriveOrder.Request request) {
        TestDriveOrder order = new TestDriveOrder(
            UUID.randomUUID(),
            request.car(),
            request.customer(),
            request.date()
        );

        repository.createOrder(order);

        return new CreateTestDriveOrder.Response(order);
    }
}
