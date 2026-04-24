package ng.saywich.shared.domain.purchasing.order.states;

public class WaitingForDeliveryOrderState implements IOrderState {
    @Override
    public OrderState getState() {
        return OrderState.WAITING_FOR_DELIVERY;
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
        return false;
    }

    @Override
    public Boolean canWaitForDelivery() {
        return false;
    }

    @Override
    public Boolean canDeliverCar() {
        return true;
    }

    @Override
    public Boolean canBeReady() { return false; }

    @Override
    public Boolean canComplete() {
        return false;
    }

    @Override
    public Boolean canCancel() {
        return false;
    }
}
