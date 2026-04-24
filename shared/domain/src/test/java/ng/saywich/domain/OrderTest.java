package ng.saywich.domain;

import ng.saywich.shared.domain.car.Car;
import ng.saywich.shared.domain.exceptions.IncompatibleOrderStateException;
import ng.saywich.shared.domain.purchasing.Customer;
import ng.saywich.shared.domain.purchasing.Manager;
import ng.saywich.shared.domain.purchasing.order.CarInStockOrder;
import ng.saywich.shared.domain.purchasing.order.CarManufactureOrder;
import ng.saywich.shared.domain.purchasing.order.states.OrderState;
import ng.saywich.shared.domain.purchasing.order.states.PendingOrderState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class OrderTest {
    @Test void normalCarInStockOrderFlowTest() {
        CarModelMockRepository repository = new CarModelMockRepository();

        Manager manager = new Manager(UUID.randomUUID(), "Sashka");
        Customer customer = new Customer(UUID.randomUUID(), "Antoha");

        CarInStockOrder order = new CarInStockOrder(
            UUID.randomUUID(),
            new PendingOrderState(),
            manager,
            customer,
            UUID.randomUUID()
        );

        Assertions.assertDoesNotThrow(order::accept);
        Assertions.assertDoesNotThrow(order::waitForPayment);
        Assertions.assertDoesNotThrow(order::pay);
        Assertions.assertDoesNotThrow(order::ready);
        Assertions.assertDoesNotThrow(order::complete);

        Assertions.assertEquals(OrderState.COMPLETED, order.getOrderState().getState());
    }

    @Test void normalCarManufactureOrderFlowTest() {
        CarModelMockRepository repository = new CarModelMockRepository();

        Manager manager = new Manager(UUID.randomUUID(), "Sashka");
        Customer customer = new Customer(UUID.randomUUID(), "Antoha");

        CarManufactureOrder order = new CarManufactureOrder(
            UUID.randomUUID(),
            new PendingOrderState(),
            manager,
            customer,
            UUID.randomUUID(),
            UUID.randomUUID()
        );

        Assertions.assertDoesNotThrow(order::accept);
        Assertions.assertDoesNotThrow(order::waitForPayment);
        Assertions.assertDoesNotThrow(order::pay);
        Assertions.assertDoesNotThrow(order::waitForDelivery);
        Assertions.assertDoesNotThrow(order::deliverCar);
        Assertions.assertDoesNotThrow(order::complete);

        Assertions.assertEquals(OrderState.COMPLETED, order.getOrderState().getState());
    }

    @Test void cancelledCarInStockOrderFlowTest() {
        CarModelMockRepository repository = new CarModelMockRepository();

        Manager manager = new Manager(UUID.randomUUID(), "Sashka");
        Customer customer = new Customer(UUID.randomUUID(), "Antoha");

        CarInStockOrder order = new CarInStockOrder(
            UUID.randomUUID(),
            new PendingOrderState(),
            manager,
            customer,
            UUID.randomUUID()
        );

        Assertions.assertDoesNotThrow(order::cancel);
        Assertions.assertThrows(IncompatibleOrderStateException.class, order::accept);
        Assertions.assertThrows(IncompatibleOrderStateException.class, order::waitForPayment);
        Assertions.assertThrows(IncompatibleOrderStateException.class, order::pay);
        Assertions.assertThrows(IncompatibleOrderStateException.class, order::complete);

        Assertions.assertEquals(OrderState.CANCELLED, order.getOrderState().getState());
    }

    @Test void cancelledCarManufactureOrderFlowTest() {
        CarModelMockRepository repository = new CarModelMockRepository();

        Manager manager = new Manager(UUID.randomUUID(), "Sashka");
        Customer customer = new Customer(UUID.randomUUID(), "Antoha");

        CarManufactureOrder order = new CarManufactureOrder(
            UUID.randomUUID(),
            new PendingOrderState(),
            manager,
            customer,
            UUID.randomUUID(),
            UUID.randomUUID()
        );

        Assertions.assertDoesNotThrow(order::cancel);
        Assertions.assertThrows(IncompatibleOrderStateException.class, order::accept);
        Assertions.assertThrows(IncompatibleOrderStateException.class, order::waitForPayment);
        Assertions.assertThrows(IncompatibleOrderStateException.class, order::pay);
        Assertions.assertThrows(IncompatibleOrderStateException.class, order::waitForDelivery);
        Assertions.assertThrows(IncompatibleOrderStateException.class, order::deliverCar);
        Assertions.assertThrows(IncompatibleOrderStateException.class, order::complete);

        Assertions.assertEquals(OrderState.CANCELLED, order.getOrderState().getState());
    }
}
