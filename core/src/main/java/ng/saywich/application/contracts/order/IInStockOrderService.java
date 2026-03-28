package ng.saywich.application.contracts.order;

import ng.saywich.application.contracts.order.operations.*;

public interface IInStockOrderService {
    CreateInStockOrder.Response create(CreateInStockOrder.Request request);

    AcceptOrder.Response acceptOrder(AcceptOrder.Request request);

    WaitForPaymentOrder.Response waitForPaymentOrder(WaitForPaymentOrder.Request request);

    PayOrder.Response payOrder(PayOrder.Request request);

    CompleteOrder.Response completeOrder(CompleteOrder.Request request);

    CancelOrder.Response cancelOrder(CancelOrder.Request request);
}
