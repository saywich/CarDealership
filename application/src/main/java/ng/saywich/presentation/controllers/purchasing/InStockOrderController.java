package ng.saywich.presentation.controllers.purchasing;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ng.saywich.application.contracts.order.IInStockOrderService;
import ng.saywich.application.contracts.order.operations.*;
import ng.saywich.presentation.dtos.purchasing.InStockOrderDto;
import ng.saywich.presentation.mappers.purchasing.InStockOrderDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<InStockOrderDto> createOrder(@RequestBody CreateInStockOrder.Request request) {
        return ResponseEntity.ok(mapper.toOrderDto(service.create(request).order()));
    }

    @PostMapping("/accept")
    @ApiResponse(responseCode = "200", description = "Order accepted")
    @ApiResponse(responseCode = "400", description = "Invalid state transition for order")
    public ResponseEntity<AcceptOrder.Response> acceptOrder(@RequestBody AcceptOrder.Request request) {
        return ResponseEntity.ok(service.acceptOrder(request));
    }

    @PostMapping("/waitForPayment")
    @ApiResponse(responseCode = "200", description = "Order waiting for payment")
    @ApiResponse(responseCode = "400", description = "Invalid state transition for order")
    public ResponseEntity<WaitForPaymentOrder.Response> waitForPaymentOrder(
        @RequestBody WaitForPaymentOrder.Request request
    ) {
        return ResponseEntity.ok(service.waitForPaymentOrder(request));
    }

    @PostMapping("/pay")
    @ApiResponse(responseCode = "200", description = "Order paid")
    @ApiResponse(responseCode = "400", description = "Invalid state transition for order")
    public ResponseEntity<PayOrder.Response> payOrder(
        @RequestBody PayOrder.Request request
    ) {
        return ResponseEntity.ok(service.payOrder(request));
    }

    @PostMapping("/complete")
    @ApiResponse(responseCode = "200", description = "Order completed")
    @ApiResponse(responseCode = "400", description = "Invalid state transition for order")
    public ResponseEntity<CompleteOrder.Response> completeOrder(
        @RequestBody CompleteOrder.Request request
    ) {
        return ResponseEntity.ok(service.completeOrder(request));
    }

    @PostMapping("/cancel")
    @ApiResponse(responseCode = "200", description = "Order cancelled")
    @ApiResponse(responseCode = "400", description = "Invalid state transition for order")
    public ResponseEntity<CancelOrder.Response> cancelOrder(
        @RequestBody CancelOrder.Request request
    ) {
        return ResponseEntity.ok(service.cancelOrder(request));
    }
}
