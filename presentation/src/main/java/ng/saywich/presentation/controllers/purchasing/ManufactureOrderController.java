package ng.saywich.presentation.controllers.purchasing;

import ng.saywich.application.contracts.order.IManufactureOrderService;
import ng.saywich.application.contracts.order.operations.*;
import ng.saywich.presentation.dtos.purchasing.ManufactureOrderDto;
import ng.saywich.presentation.mappers.purchasing.ManufactureOrderDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manufacture_orders")
public class ManufactureOrderController {
    @Autowired
    IManufactureOrderService service;

    @Autowired
    ManufactureOrderDtoMapper mapper;

    @PostMapping("/")
    public ManufactureOrderDto createOrder(@RequestBody CreateManufactureOrder.Request request) {
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

    @PostMapping("/wait_for_delivery")
    public WaitForDeliveryOrder.Response waitForDeliveryOrder(
        @RequestBody WaitForDeliveryOrder.Request request
    ) {
        return service.waitForDeliveryOrder(request);
    }

    @PostMapping("/deliver_car")
    public DeliverCarOrder.Response waitForDeliveryOrder(
        @RequestBody DeliverCarOrder.Request request
    ) {
        return service.deliverCarOrder(request);
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
