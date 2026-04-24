package ng.saywich.shared.domain.purchasing.order.states;

public interface IOrderState {
    OrderState getState();

    Boolean canAccept();

    Boolean canWaitForPayment();

    Boolean canBePaid();

    Boolean canWaitForDelivery();

    Boolean canDeliverCar();

    Boolean canBeReady();

    Boolean canComplete();

    Boolean canCancel();
}
