package ng.saywich.presentation.controllers.purchasing;

import ng.saywich.application.contracts.order.IInStockOrderService;
import ng.saywich.application.contracts.order.operations.*;
import ng.saywich.domain.exceptions.IncompatibleOrderStateException;
import ng.saywich.presentation.dtos.purchasing.InStockOrderDto;
import ng.saywich.presentation.mappers.purchasing.InStockOrderDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("in_stock_orders")
public class InStockOrderController {
    @Autowired
    IInStockOrderService service;

    @Autowired
    InStockOrderDtoMapper mapper;

    @PostMapping("/")
    public InStockOrderDto createOrder(@RequestBody CreateInStockOrder.Request request) {
        return mapper.toOrderDto(service.create(request).order());
    }

    @PostMapping("/accept")
    public AcceptOrder.Response acceptOrder(@RequestBody AcceptOrder.Request request) {
        return service.acceptOrder(request);
    }

    @PostMapping("/waitForPayment")
    public WaitForPaymentOrder.Response waitForPaymentOrder(
        @RequestBody WaitForPaymentOrder.Request request
    ) {
        return service.waitForPaymentOrder(request);
    }

    @PostMapping("/pay")
    public PayOrder.Response payOrder(
        @RequestBody PayOrder.Request request
    ) {
        return service.payOrder(request);
    }

    @PostMapping("/complete")
    public CompleteOrder.Response completeOrder(
        @RequestBody CompleteOrder.Request request
    ) {
        return service.completeOrder(request);
    }

    @PostMapping("/cancel")
    public CancelOrder.Response cancelOrder(
        @RequestBody CancelOrder.Request request
    ) {
        return service.cancelOrder(request);
    }
}
