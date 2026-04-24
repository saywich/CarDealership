package ng.saywich.shared.domain.purchasing.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ng.saywich.shared.domain.exceptions.IncompatibleOrderStateException;
import ng.saywich.shared.domain.purchasing.Customer;
import ng.saywich.shared.domain.purchasing.Manager;
import ng.saywich.shared.domain.purchasing.order.states.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CarInStockOrder {
    UUID orderId;

    IOrderState orderState;

    private Manager manager;

    private Customer customer;

    private UUID carId;

    public void accept() throws IncompatibleOrderStateException {
        if (!orderState.canAccept())
            throw new IncompatibleOrderStateException("Order can't be accepted.");

        orderState = new AcceptedOrderState();
    }

    public void waitForPayment() throws IncompatibleOrderStateException {
        if (!orderState.canWaitForPayment())
            throw new IncompatibleOrderStateException("Order can't wait for payment.");

        orderState = new WaitingForPaymentOrderState();
    }

    public void pay() throws IncompatibleOrderStateException {
        if (!orderState.canBePaid())
            throw new IncompatibleOrderStateException("Order can't be paid.");

        orderState = new PaidOrderState();
    }

    public void ready() throws IncompatibleOrderStateException {
        if (!orderState.canBeReady())
            throw new IncompatibleOrderStateException("Order can't be paid.");

        orderState = new ReadyOrderState();
    }

    public void complete() throws IncompatibleOrderStateException {
        if (!orderState.canComplete())
            throw new IncompatibleOrderStateException("Order can't be completed");

        orderState = new CompletedOrderState();
    }

    public void cancel() throws IncompatibleOrderStateException {
        if (!orderState.canCancel())
            throw new IncompatibleOrderStateException("Order can't be cancelled.");

        orderState = new CancelledOrderState();
    }
}
