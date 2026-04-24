package ng.saywich.shared.domain.purchasing.order.states;

public class ReadyOrderState implements IOrderState {
    @Override
    public OrderState getState() {
        return OrderState.READY;
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
        return false;
    }

    @Override
    public Boolean canBeReady() { return false; }

    @Override
    public Boolean canComplete() {
        return true;
    }

    @Override
    public Boolean canCancel() {
        return false;
    }
}