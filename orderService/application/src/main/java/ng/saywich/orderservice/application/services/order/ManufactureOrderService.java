package ng.saywich.orderservice.application.services.order;

import lombok.AllArgsConstructor;
import ng.saywich.orderservice.application.abstractions.order.ICustomerRepository;
import ng.saywich.orderservice.application.abstractions.order.IManagerRepository;
import ng.saywich.orderservice.application.abstractions.order.IManufactureOrderRepository;
import ng.saywich.orderservice.application.contracts.mappers.purchasing.ManufactureOrderDtoMapper;
import ng.saywich.orderservice.application.contracts.order.IManufactureOrderService;
import ng.saywich.orderservice.application.contracts.order.operations.*;
import ng.saywich.orderservice.application.services.order.publishers.ManufactureOrderPublisher;
import ng.saywich.shared.domain.exceptions.DomainValidationException;
import ng.saywich.shared.domain.purchasing.Customer;
import ng.saywich.shared.domain.purchasing.Manager;
import ng.saywich.shared.domain.purchasing.order.CarManufactureOrder;
import ng.saywich.shared.domain.purchasing.order.states.PendingOrderState;
import ng.saywich.shared.events.InStockOrderRequestEvent;
import ng.saywich.shared.events.ManufactureOrderRequestEvent;
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
public class ManufactureOrderService implements IManufactureOrderService {
    @Autowired
    IManufactureOrderRepository manufactureOrderRepository;

    @Autowired
    private IManagerRepository managerRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ManufactureOrderDtoMapper orderDtoMapper;

    @Autowired
    private ManufactureOrderPublisher manufactureOrderPublisher;

    @Override
    public QueryManufactureOrder.Response query(QueryManufactureOrder.Request request) {
        if (request.filter() != null) {
            return new QueryManufactureOrder.Response(
                manufactureOrderRepository.queryWithFilter(request.filter()).stream()
                    .map(orderDtoMapper::toOrderDto)
                    .toList()
            );
        }

        return new QueryManufactureOrder.Response(
            manufactureOrderRepository.query(request.ids()).stream()
                .map(orderDtoMapper::toOrderDto)
                .toList()
        );
    }

    @Override
    public CreateManufactureOrder.Response create(CreateManufactureOrder.Request request) {
        List<Manager> managerList = managerRepository.query(new UUID[0]);
        Optional<Customer> customerOpt = customerRepository.queryById(request.customerId());

        if (customerOpt.isEmpty()) {
            throw new DomainValidationException("Can't find such user");
        }

        CarManufactureOrder order = new CarManufactureOrder(
            UUID.randomUUID(),
            new PendingOrderState(),
            managerList.get(ThreadLocalRandom.current().nextInt(managerList.size())),
            customerOpt.get(),
            request.carModelId(),
            request.optionSetId()
        );

        manufactureOrderRepository.createOrder(order);

        return new CreateManufactureOrder.Response(orderDtoMapper.toOrderDto(order));
    }

    @Override
    public AcceptOrder.Response acceptOrder(AcceptOrder.Request request) {
        CarManufactureOrder order = manufactureOrderRepository.query(
            new UUID[] { request.orderId() }).get(0);

        order.accept();

        manufactureOrderRepository.updateOrder(order);

        return new AcceptOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public WaitForPaymentOrder.Response waitForPaymentOrder(WaitForPaymentOrder.Request request) {
        CarManufactureOrder order = manufactureOrderRepository.query(
            new UUID[] { request.orderId() }).get(0);

        order.waitForPayment();

        manufactureOrderRepository.updateOrder(order);

        return new WaitForPaymentOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    @Transactional
    public PayOrder.Response payOrder(PayOrder.Request request) {
        CarManufactureOrder order = manufactureOrderRepository.query(
            new UUID[] { request.orderId() }).get(0);

        order.pay();

        manufactureOrderRepository.updateOrder(order);

        manufactureOrderPublisher.publish(new ManufactureOrderRequestEvent(
            UUID.randomUUID(),
            order.getOrderId(),
            order.getCarModelId(),
            order.getOptionSetId(),
            order.getCustomer().customerId(),
            Date.from(Instant.now())
        ));

        return new PayOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public WaitForDeliveryOrder.Response waitForDeliveryOrder(WaitForDeliveryOrder.Request request) {
        CarManufactureOrder order = manufactureOrderRepository.query(
            new UUID[] { request.orderId() }).get(0);

        order.waitForDelivery();

        manufactureOrderRepository.updateOrder(order);

        return new WaitForDeliveryOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public DeliverCarOrder.Response deliverCarOrder(DeliverCarOrder.Request request) {
        CarManufactureOrder order = manufactureOrderRepository.query(
            new UUID[] { request.orderId() }).get(0);

        order.deliverCar();

        manufactureOrderRepository.updateOrder(order);

        return new DeliverCarOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public CompleteOrder.Response completeOrder(CompleteOrder.Request request) {
        CarManufactureOrder order = manufactureOrderRepository.query(
            new UUID[] { request.orderId() }).get(0);

        order.complete();

        manufactureOrderRepository.updateOrder(order);

        return new CompleteOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public CancelOrder.Response cancelOrder(CancelOrder.Request request) {
        CarManufactureOrder order = manufactureOrderRepository.query(
            new UUID[] { request.orderId() }).get(0);

        order.cancel();

        manufactureOrderRepository.updateOrder(order);

        return new CancelOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }
}
