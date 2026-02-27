package ng.saywich.domain.purchasing.order.states;

public class WaitingForPaymentOrderState implements IOrderState {
    @Override
    public OrderState getState() {
        return OrderState.WAITING_FOR_PAYMENT;
    }

    @Override
    public Boolean canAccept() {
        return false;
    }

    @Override
    public Boolean canWaitForPayment() {
        return false;
    }

    @Override
    public Boolean canBePaid() {
        return true;
    }

    @Override
    public Boolean canWaitForDelivery() {
        return false;
    }

    @Override
    public Boolean canDeliverCar() {
        return false;
    }

    @Override
    public Boolean canComplete() {
        return false;
    }

    @Override
    public Boolean canCancel() {
        return true;
    }
}
