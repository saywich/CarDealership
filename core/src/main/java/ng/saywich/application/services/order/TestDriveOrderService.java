package ng.saywich.application.services.order;

import lombok.AllArgsConstructor;
import ng.saywich.application.abstractions.order.ITestDriveOrderRepository;
import ng.saywich.application.contracts.order.ITestDriveOrderService;
import ng.saywich.application.contracts.order.operations.CreateTestDriveOrder;
import ng.saywich.domain.purchasing.order.TestDriveOrder;
import ng.saywich.application.presentationmappers.cars.CarDtoMapper;
import ng.saywich.application.presentationmappers.purchasing.CustomerDtoMapper;
import ng.saywich.application.presentationmappers.purchasing.TestDriveOrderDtoMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TestDriveOrderService implements ITestDriveOrderService {
    ITestDriveOrderRepository repository;

    private CustomerDtoMapper customerDtoMapper;

    private CarDtoMapper carDtoMapper;

    private TestDriveOrderDtoMapper orderDtoMapper;

    @Override
    public CreateTestDriveOrder.Response create(CreateTestDriveOrder.Request request) {
        TestDriveOrder order = new TestDriveOrder(
            UUID.randomUUID(),
            carDtoMapper.toCar(request.car()),
            customerDtoMapper.toCustomer(request.customer()),
            request.date()
        );

        repository.createOrder(order);

        return new CreateTestDriveOrder.Response(orderDtoMapper.toOrderDto(order));
    }
}
