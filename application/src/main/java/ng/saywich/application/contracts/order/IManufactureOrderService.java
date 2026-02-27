package ng.saywich.application.contracts.order;

import ng.saywich.application.contracts.order.operations.*;

public interface IManufactureOrderService {
    CreateManufactureOrder.Response create(CreateManufactureOrder.Request request);

    AcceptOrder.Response acceptOrder(AcceptOrder.Request request);

    WaitForPaymentOrder.Response waitForPaymentOrder(WaitForPaymentOrder.Request request);

    PayOrder.Response payOrder(PayOrder.Request request);

    WaitForDeliveryOrder.Response waitForDeliveryOrder(WaitForDeliveryOrder.Request request);

    DeliverCarOrder.Response deliverCarOrder(DeliverCarOrder.Request request);

    CompleteOrder.Response completeOrder(CompleteOrder.Request request);

    CancelOrder.Response cancelOrder(CancelOrder.Request request);
}
