package ng.saywich.domain.purchasing.order.states;

public interface IOrderState {
    OrderState getState();

    Boolean canAccept();

    Boolean canWaitForPayment();

    Boolean canBePaid();

    Boolean canWaitForDelivery();

    Boolean canDeliverCar();

    Boolean canComplete();

    Boolean canCancel();
}
