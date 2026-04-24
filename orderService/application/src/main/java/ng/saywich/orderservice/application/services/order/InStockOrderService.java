package ng.saywich.orderservice.application.services.order;

import lombok.AllArgsConstructor;
import ng.saywich.orderservice.application.abstractions.order.ICustomerRepository;
import ng.saywich.orderservice.application.abstractions.order.IInStockOrderRepository;
import ng.saywich.orderservice.application.abstractions.order.IManagerRepository;
import ng.saywich.orderservice.application.contracts.mappers.purchasing.InStockOrderDtoMapper;
import ng.saywich.orderservice.application.contracts.order.IInStockOrderService;
import ng.saywich.orderservice.application.contracts.order.operations.*;
import ng.saywich.orderservice.application.services.order.publishers.InStockOrderPublisher;
import ng.saywich.shared.domain.exceptions.DomainValidationException;
import ng.saywich.shared.domain.purchasing.Customer;
import ng.saywich.shared.domain.purchasing.Manager;
import ng.saywich.shared.domain.purchasing.order.CarInStockOrder;
import ng.saywich.shared.domain.purchasing.order.states.PendingOrderState;
import ng.saywich.shared.events.InStockOrderRequestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class InStockOrderService implements IInStockOrderService {
    @Autowired
    private IInStockOrderRepository inStockOrderRepository;

    @Autowired
    private IManagerRepository managerRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private InStockOrderDtoMapper orderDtoMapper;

    @Autowired
    private InStockOrderPublisher inStockOrderPublisher;

    @Override
    public QueryInStockOrder.Response query(QueryInStockOrder.Request request) {
        if (request.filter() != null) {
            return new QueryInStockOrder.Response(
                inStockOrderRepository.queryWithFilter(request.filter()).stream()
                    .map(orderDtoMapper::toOrderDto)
                    .toList()
            );
        }

        return new QueryInStockOrder.Response(
            inStockOrderRepository.query(request.ids()).stream()
                .map(orderDtoMapper::toOrderDto)
                .toList()
        );
    }

    @Override
    public CreateInStockOrder.Response create(CreateInStockOrder.Request request) {
        List<Manager> managerList = managerRepository.query(new UUID[0]);
        Optional<Customer> customerOpt = customerRepository.queryById(request.customerId());

        if (customerOpt.isEmpty()) {
            throw new DomainValidationException("Can't find such user");
        }

        CarInStockOrder order = new CarInStockOrder(
            UUID.randomUUID(),
            new PendingOrderState(),
            managerList.get(ThreadLocalRandom.current().nextInt(managerList.size())),
            customerOpt.get(),
            request.carId()
        );

        inStockOrderRepository.createOrder(order);

        return new CreateInStockOrder.Response(orderDtoMapper.toOrderDto(order));
    }

    @Override
    public AcceptOrder.Response acceptOrder(AcceptOrder.Request request) {
        CarInStockOrder order = inStockOrderRepository.query(
            new UUID[] { request.orderId() }).get(0);

        order.accept();

        inStockOrderRepository.updateOrder(order);

        return new AcceptOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public WaitForPaymentOrder.Response waitForPaymentOrder(WaitForPaymentOrder.Request request) {
        CarInStockOrder order = inStockOrderRepository.query(
            new UUID[] { request.orderId() }).get(0);

        order.waitForPayment();

        inStockOrderRepository.updateOrder(order);

        return new WaitForPaymentOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    @Transactional
    public PayOrder.Response payOrder(PayOrder.Request request) {
        CarInStockOrder order = inStockOrderRepository.query(
            new UUID[] { request.orderId() }).get(0);

        order.pay();

        inStockOrderRepository.updateOrder(order);

        inStockOrderPublisher.publish(new InStockOrderRequestEvent(
            UUID.randomUUID(),
            order.getOrderId(),
            order.getCarId(),
            order.getCustomer().customerId(),
            Date.from(Instant.now())
        ));

        return new PayOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public PrepareOrder.Response prepareOrder(PrepareOrder.Request request) {
        CarInStockOrder order = inStockOrderRepository.query(
            new UUID[] { request.orderId() }).get(0);

        order.ready();

        inStockOrderRepository.updateOrder(order);

        return new PrepareOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public CompleteOrder.Response completeOrder(CompleteOrder.Request request) {
        CarInStockOrder order = inStockOrderRepository.query(
            new UUID[] { request.orderId() }).get(0);

        order.complete();

        inStockOrderRepository.updateOrder(order);

        return new CompleteOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public CancelOrder.Response cancelOrder(CancelOrder.Request request) {
        CarInStockOrder order = inStockOrderRepository.query(
            new UUID[] { request.orderId() }).get(0);

        order.cancel();

        inStockOrderRepository.updateOrder(order);

        return new CancelOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }
}
