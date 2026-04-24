package ng.saywich.orderservice.application.services.order;

import lombok.AllArgsConstructor;
import ng.saywich.orderservice.application.abstractions.order.ICustomerRepository;
import ng.saywich.orderservice.application.abstractions.order.ITestDriveOrderRepository;
import ng.saywich.orderservice.application.contracts.mappers.purchasing.TestDriveOrderDtoMapper;
import ng.saywich.orderservice.application.contracts.order.ITestDriveOrderService;
import ng.saywich.orderservice.application.contracts.order.operations.CreateTestDriveOrder;
import ng.saywich.orderservice.application.services.order.publishers.TestDriveOrderPublisher;
import ng.saywich.shared.domain.exceptions.DomainValidationException;
import ng.saywich.shared.domain.purchasing.Customer;
import ng.saywich.shared.domain.purchasing.order.TestDriveOrder;
import ng.saywich.shared.events.TestDriveOrderRequestEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TestDriveOrderService implements ITestDriveOrderService {
    ITestDriveOrderRepository testDriveOrderRepository;

    private TestDriveOrderDtoMapper orderDtoMapper;

    private ICustomerRepository customerRepository;

    private TestDriveOrderPublisher testDriveOrderPublisher;

    @Override
    @Transactional
    public CreateTestDriveOrder.Response create(CreateTestDriveOrder.Request request) {
        Optional<Customer> customerOpt = customerRepository.queryById(request.customerId());

        if (customerOpt.isEmpty()) {
            throw new DomainValidationException("Can't find such user");
        }

        TestDriveOrder order = new TestDriveOrder(
            UUID.randomUUID(),
            request.carId(),
            customerOpt.get(),
            request.date()
        );

        testDriveOrderRepository.createOrder(order);

        testDriveOrderPublisher.publish(new TestDriveOrderRequestEvent(
            UUID.randomUUID(),
            order.getOrderId(),
            order.getRequestedCarId(),
            order.getCustomer().customerId(),
            Date.from(Instant.now())
        ));

        return new CreateTestDriveOrder.Response(orderDtoMapper.toOrderDto(order));
    }
}
