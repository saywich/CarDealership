package ng.saywich.domain.purchasing.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.car.parts.OptionSet;
import ng.saywich.domain.exceptions.IncompatibleOrderStateException;
import ng.saywich.domain.purchasing.Customer;
import ng.saywich.domain.purchasing.Manager;
import ng.saywich.domain.purchasing.order.states.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CarManufactureOrder {
    UUID orderId;

    IOrderState orderState;

    private Manager manager;

    private Customer customer;

    private CarModel carModel;

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

    public void waitForDelivery() throws IncompatibleOrderStateException {
        if (!orderState.canWaitForDelivery())
            throw new IncompatibleOrderStateException("Order can't wait for delivery.");

        orderState = new WaitingForDeliveryOrderState();
    }

    public void deliverCar() throws IncompatibleOrderStateException {
        if (!orderState.canDeliverCar())
            throw new IncompatibleOrderStateException("Order can't deliver car.");

        orderState = new ReadyOrderState();
    }

    public void complete() throws IncompatibleOrderStateException {
        if (!orderState.canComplete())
            throw new IncompatibleOrderStateException("Order can't be completed.");

        orderState = new CompletedOrderState();
    }

    public void cancel() throws IncompatibleOrderStateException {
        if (!orderState.canCancel())
            throw new IncompatibleOrderStateException("Order can't be cancelled.");

        orderState = new CancelledOrderState();
    }
}
