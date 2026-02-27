package ng.saywich.domain.purchasing.order.states;

public class CancelledOrderState implements IOrderState {
    @Override
    public OrderState getState() {
        return OrderState.CANCELLED;
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
    public Boolean canComplete() {
        return false;
    }

    @Override
    public Boolean canCancel() {
        return false;
    }
}
