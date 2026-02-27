package ng.saywich.application.services.order;

import lombok.AllArgsConstructor;
import ng.saywich.application.abstractions.order.IManufactureOrderRepository;
import ng.saywich.application.contracts.order.IManufactureOrderService;
import ng.saywich.application.contracts.order.operations.*;
import ng.saywich.domain.purchasing.order.CarInStockOrder;
import ng.saywich.domain.purchasing.order.CarManufactureOrder;
import ng.saywich.domain.purchasing.order.states.PendingOrderState;

import java.util.UUID;

@AllArgsConstructor
public class ManufactureOrderService implements IManufactureOrderService {
    IManufactureOrderRepository repository;

    @Override
    public CreateManufactureOrder.Response create(CreateManufactureOrder.Request request) {
        CarManufactureOrder order = new CarManufactureOrder(
            UUID.randomUUID(),
            new PendingOrderState(),
            request.manager(),
            request.customer(),
            request.carModel()
        );

        repository.createOrder(order);

        return new CreateManufactureOrder.Response(order);
    }

    @Override
    public AcceptOrder.Response acceptOrder(AcceptOrder.Request request) {
        CarManufactureOrder order = repository.query(
            new UUID[] { request.orderId() }).getFirst();

        order.accept();

        return new AcceptOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public WaitForPaymentOrder.Response waitForPaymentOrder(WaitForPaymentOrder.Request request) {
        CarManufactureOrder order = repository.query(
            new UUID[] { request.orderId() }).getFirst();

        order.waitForPayment();

        return new WaitForPaymentOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public PayOrder.Response payOrder(PayOrder.Request request) {
        CarManufactureOrder order = repository.query(
            new UUID[] { request.orderId() }).getFirst();

        order.pay();

        return new PayOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public WaitForDeliveryOrder.Response waitForDeliveryOrder(WaitForDeliveryOrder.Request request) {
        CarManufactureOrder order = repository.query(
            new UUID[] { request.orderId() }).getFirst();

        order.waitForDelivery();

        return new WaitForDeliveryOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public DeliverCarOrder.Response deliverCarOrder(DeliverCarOrder.Request request) {
        CarManufactureOrder order = repository.query(
            new UUID[] { request.orderId() }).getFirst();

        order.deliverCar();

        return new DeliverCarOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public CompleteOrder.Response completeOrder(CompleteOrder.Request request) {
        CarManufactureOrder order = repository.query(
            new UUID[] { request.orderId() }).getFirst();

        order.complete();

        return new CompleteOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public CancelOrder.Response cancelOrder(CancelOrder.Request request) {
        CarManufactureOrder order = repository.query(
            new UUID[] { request.orderId() }).getFirst();

        order.cancel();

        return new CancelOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }
}
