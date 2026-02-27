package ng.saywich.application.services.order;

import lombok.AllArgsConstructor;
import ng.saywich.application.abstractions.order.IInStockOrderRepository;
import ng.saywich.application.contracts.order.IInStockOrderService;
import ng.saywich.application.contracts.order.operations.*;
import ng.saywich.domain.purchasing.order.CarInStockOrder;
import ng.saywich.domain.purchasing.order.states.PendingOrderState;

import java.util.UUID;

@AllArgsConstructor
public class InStockOrderService implements IInStockOrderService {
    private IInStockOrderRepository repository;

    @Override
    public CreateInStockOrder.Response create(CreateInStockOrder.Request request) {
        CarInStockOrder order = new CarInStockOrder(
            UUID.randomUUID(),
            new PendingOrderState(),
            request.manager(),
            request.customer(),
            request.car()
        );

        repository.createOrder(order);

        return new CreateInStockOrder.Response(order);
    }

    @Override
    public AcceptOrder.Response acceptOrder(AcceptOrder.Request request) {
        CarInStockOrder order = repository.query(
            new UUID[] { request.orderId() }).getFirst();

        order.accept();

        return new AcceptOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public WaitForPaymentOrder.Response waitForPaymentOrder(WaitForPaymentOrder.Request request) {
        CarInStockOrder order = repository.query(
            new UUID[] { request.orderId() }).getFirst();

        order.waitForPayment();

        return new WaitForPaymentOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public PayOrder.Response payOrder(PayOrder.Request request) {
        CarInStockOrder order = repository.query(
            new UUID[] { request.orderId() }).getFirst();

        order.pay();

        return new PayOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public CompleteOrder.Response completeOrder(CompleteOrder.Request request) {
        CarInStockOrder order = repository.query(
            new UUID[] { request.orderId() }).getFirst();

        order.complete();

        return new CompleteOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public CancelOrder.Response cancelOrder(CancelOrder.Request request) {
        CarInStockOrder order = repository.query(
            new UUID[] { request.orderId() }).getFirst();

        order.cancel();

        return new CancelOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }
}
