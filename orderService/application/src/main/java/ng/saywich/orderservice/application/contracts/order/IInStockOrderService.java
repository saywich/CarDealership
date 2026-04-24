package ng.saywich.orderservice.application.contracts.order;

import ng.saywich.orderservice.application.contracts.order.operations.*;

public interface IInStockOrderService {
    QueryInStockOrder.Response query(QueryInStockOrder.Request request);

    CreateInStockOrder.Response create(CreateInStockOrder.Request request);

    AcceptOrder.Response acceptOrder(AcceptOrder.Request request);

    WaitForPaymentOrder.Response waitForPaymentOrder(WaitForPaymentOrder.Request request);

    PayOrder.Response payOrder(PayOrder.Request request);

    PrepareOrder.Response prepareOrder(PrepareOrder.Request request);

    CompleteOrder.Response completeOrder(CompleteOrder.Request request);

    CancelOrder.Response cancelOrder(CancelOrder.Request request);
}
