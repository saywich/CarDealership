package ng.saywich.application.services.order;

import lombok.AllArgsConstructor;
import ng.saywich.application.abstractions.order.IInStockOrderRepository;
import ng.saywich.application.contracts.order.IInStockOrderService;
import ng.saywich.application.abstractions.order.IManagerRepository;
import ng.saywich.application.contracts.order.operations.*;
import ng.saywich.domain.purchasing.Manager;
import ng.saywich.domain.purchasing.order.CarInStockOrder;
import ng.saywich.domain.purchasing.order.states.PendingOrderState;
import ng.saywich.application.presentationmappers.cars.CarDtoMapper;
import ng.saywich.application.presentationmappers.purchasing.CustomerDtoMapper;
import ng.saywich.application.presentationmappers.purchasing.InStockOrderDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.random.RandomGenerator;

@Service
@AllArgsConstructor
public class InStockOrderService implements IInStockOrderService {
    @Autowired
    private IInStockOrderRepository repository;

    @Autowired
    private IManagerRepository managerRepository;

    @Autowired
    private CustomerDtoMapper customerDtoMapper;

    @Autowired
    private CarDtoMapper carDtoMapper;

    @Autowired
    private InStockOrderDtoMapper orderDtoMapper;

    @Override
    public CreateInStockOrder.Response create(CreateInStockOrder.Request request) {
        List<Manager> managerList = managerRepository.query(new UUID[0]);

        CarInStockOrder order = new CarInStockOrder(
            UUID.randomUUID(),
            new PendingOrderState(),
            managerList.get(RandomGenerator.getDefault()
                .nextInt(0, managerList.size())),
            customerDtoMapper.toCustomer(request.customer()),
            carDtoMapper.toCar(request.car())
        );

        repository.createOrder(order);

        return new CreateInStockOrder.Response(orderDtoMapper.toOrderDto(order));
    }

    @Override
    public AcceptOrder.Response acceptOrder(AcceptOrder.Request request) {
        CarInStockOrder order = repository.query(
            new UUID[] { request.orderId() }).get(0);

        order.accept();

        return new AcceptOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public WaitForPaymentOrder.Response waitForPaymentOrder(WaitForPaymentOrder.Request request) {
        CarInStockOrder order = repository.query(
            new UUID[] { request.orderId() }).get(0);

        order.waitForPayment();

        return new WaitForPaymentOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public PayOrder.Response payOrder(PayOrder.Request request) {
        CarInStockOrder order = repository.query(
            new UUID[] { request.orderId() }).get(0);

        order.pay();

        return new PayOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public CompleteOrder.Response completeOrder(CompleteOrder.Request request) {
        CarInStockOrder order = repository.query(
            new UUID[] { request.orderId() }).get(0);

        order.complete();

        return new CompleteOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }

    @Override
    public CancelOrder.Response cancelOrder(CancelOrder.Request request) {
        CarInStockOrder order = repository.query(
            new UUID[] { request.orderId() }).get(0);

        order.cancel();

        return new CancelOrder.Response(order.getOrderId(), order.getOrderState().getState());
    }
}
