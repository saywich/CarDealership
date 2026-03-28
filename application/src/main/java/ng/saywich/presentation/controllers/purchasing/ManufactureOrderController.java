package ng.saywich.presentation.controllers.purchasing;

import ng.saywich.application.contracts.order.IManufactureOrderService;
import ng.saywich.application.contracts.order.operations.*;
import ng.saywich.presentation.dtos.purchasing.ManufactureOrderDto;
import ng.saywich.presentation.mappers.purchasing.ManufactureOrderDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ManufactureOrderDto> createOrder(@RequestBody CreateManufactureOrder.Request request) {
        return ResponseEntity.ok(mapper.toOrderDto(service.create(request).order()));
    }

    @PostMapping("/accept")
    public ResponseEntity<AcceptOrder.Response> acceptOrder(@RequestBody AcceptOrder.Request request) {
        return ResponseEntity.ok(service.acceptOrder(request));
    }

    @PostMapping("/waitForPayment")
    public ResponseEntity<WaitForPaymentOrder.Response> waitForPaymentOrder(
        @RequestBody WaitForPaymentOrder.Request request
    ) {
        return ResponseEntity.ok(service.waitForPaymentOrder(request));
    }

    @PostMapping("/pay")
    public ResponseEntity<PayOrder.Response> payOrder(
        @RequestBody PayOrder.Request request
    ) {
        return ResponseEntity.ok(service.payOrder(request));
    }

    @PostMapping("/wait_for_delivery")
    public ResponseEntity<WaitForDeliveryOrder.Response> waitForDeliveryOrder(
        @RequestBody WaitForDeliveryOrder.Request request
    ) {
        return ResponseEntity.ok(service.waitForDeliveryOrder(request));
    }

    @PostMapping("/deliver_car")
    public ResponseEntity<DeliverCarOrder.Response> waitForDeliveryOrder(
        @RequestBody DeliverCarOrder.Request request
    ) {
        return ResponseEntity.ok(service.deliverCarOrder(request));
    }

    @PostMapping("/complete")
    public ResponseEntity<CompleteOrder.Response> completeOrder(
        @RequestBody CompleteOrder.Request request
    ) {
        return ResponseEntity.ok(service.completeOrder(request));
    }

    @PostMapping("/cancel")
    public ResponseEntity<CancelOrder.Response> cancelOrder(
        @RequestBody CancelOrder.Request request
    ) {
        return ResponseEntity.ok(service.cancelOrder(request));
    }
}
