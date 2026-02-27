package ng.saywich.domain.purchasing.order.states;

public class AcceptedOrderState implements IOrderState {
    @Override
    public OrderState getState() {
        return OrderState.ACCEPTED;
    }

    @Override
    public Boolean canAccept() {
        return false;
    }

    @Override
    public Boolean canWaitForPayment() {
        return true;
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
        return true;
    }
}
