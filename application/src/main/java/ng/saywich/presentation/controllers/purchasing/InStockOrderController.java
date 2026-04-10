package ng.saywich.presentation.controllers.purchasing;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ng.saywich.application.contracts.order.IInStockOrderService;
import ng.saywich.application.contracts.order.operations.*;
import ng.saywich.application.contracts.dtos.purchasing.InStockOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("in_stock_orders")
public class InStockOrderController {
    @Autowired
    IInStockOrderService service;

    @GetMapping("/customer")
    @ApiResponse(responseCode = "200", description = "Order list")
    @ApiResponse(responseCode = "400", description = "Invalid data")
    @ApiResponse(responseCode = "402", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Order filter",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = QueryInStockOrder.Request.class)
        )
    )
    public ResponseEntity<List<InStockOrderDto>> getCustomerOrders(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody QueryInStockOrder.Request request
    ) {
        String userId = jwt.getClaimAsString("sub");

        if (request.filter() == null || request.filter().customerId() == null) {
            return ResponseEntity.badRequest().build();
        }

        if (request.filter().customerId().toString() != userId) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(service.query(request).orders());
    }

    @GetMapping("/manager")
    @ApiResponse(responseCode = "200", description = "Order list")
    @ApiResponse(responseCode = "400", description = "Invalid data")
    @ApiResponse(responseCode = "402", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Order filter",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = QueryInStockOrder.Request.class)
        )
    )
    public ResponseEntity<List<InStockOrderDto>> getManagerOrders(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody QueryInStockOrder.Request request
    ) {
        String userId = jwt.getClaimAsString("sub");

        if (request.filter() == null || request.filter().managerId() == null) {
            return ResponseEntity.badRequest().build();
        }

        if (request.filter().managerId().toString() != userId) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(service.query(request).orders());
    }

    @GetMapping("/all")
    @ApiResponse(responseCode = "200", description = "Order list")
    @ApiResponse(responseCode = "400", description = "Invalid data")
    @ApiResponse(responseCode = "402", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Order filter",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = QueryInStockOrder.Request.class)
        )
    )
    public ResponseEntity<List<InStockOrderDto>> getOrders(
        @RequestBody QueryInStockOrder.Request request
    ) {
        return ResponseEntity.ok(service.query(request).orders());
    }

    @PostMapping("/")
    @ApiResponse(responseCode = "200", description = "Order created")
    @ApiResponse(responseCode = "402", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Order filter",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = QueryInStockOrder.Request.class)
        )
    )
    public ResponseEntity<InStockOrderDto> createOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody CreateInStockOrder.Request request
    ) {
        return ResponseEntity.ok(service.create(request).order());
    }

    @PostMapping("/accept")
    @ApiResponse(responseCode = "200", description = "Order accepted")
    @ApiResponse(responseCode = "402", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @ApiResponse(responseCode = "400", description = "Invalid state transition for order")
    public ResponseEntity<AcceptOrder.Response> acceptOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody AcceptOrder.Request request
    ) {
        return ResponseEntity.ok(service.acceptOrder(request));
    }

    @PostMapping("/waitForPayment")
    @ApiResponse(responseCode = "200", description = "Order waiting for payment")
    @ApiResponse(responseCode = "402", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @ApiResponse(responseCode = "400", description = "Invalid state transition for order")
    public ResponseEntity<WaitForPaymentOrder.Response> waitForPaymentOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody WaitForPaymentOrder.Request request
    ) {
        return ResponseEntity.ok(service.waitForPaymentOrder(request));
    }

    @PostMapping("/pay")
    @ApiResponse(responseCode = "200", description = "Order paid")
    @ApiResponse(responseCode = "402", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @ApiResponse(responseCode = "400", description = "Invalid state transition for order")
    public ResponseEntity<PayOrder.Response> payOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody PayOrder.Request request
    ) {
        return ResponseEntity.ok(service.payOrder(request));
    }

    @PostMapping("/complete")
    @ApiResponse(responseCode = "200", description = "Order completed")
    @ApiResponse(responseCode = "402", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @ApiResponse(responseCode = "400", description = "Invalid state transition for order")
    public ResponseEntity<CompleteOrder.Response> completeOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody CompleteOrder.Request request
    ) {
        return ResponseEntity.ok(service.completeOrder(request));
    }

    @PostMapping("/cancel")
    @ApiResponse(responseCode = "200", description = "Order cancelled")
    @ApiResponse(responseCode = "402", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @ApiResponse(responseCode = "400", description = "Invalid state transition for order")
    public ResponseEntity<CancelOrder.Response> cancelOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody CancelOrder.Request request
    ) {
        return ResponseEntity.ok(service.cancelOrder(request));
    }
}
