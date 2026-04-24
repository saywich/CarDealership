package ng.saywich.shared.domain.purchasing.order.states;

public class PendingOrderState implements IOrderState {
    @Override
    public OrderState getState() {
        return OrderState.PENDING;
    }

    @Override
    public Boolean canAccept() {
        return true;
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
        return false;
    }

    @Override
    public Boolean canBeReady() { return false; }

    @Override
    public Boolean canComplete() {
        return false;
    }

    @Override
    public Boolean canCancel() {
        return true;
    }
}
