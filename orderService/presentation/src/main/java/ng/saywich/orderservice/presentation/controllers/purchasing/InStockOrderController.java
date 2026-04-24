package ng.saywich.orderservice.presentation.controllers.purchasing;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ng.saywich.orderservice.application.contracts.dtos.purchasing.InStockOrderDto;
import ng.saywich.orderservice.application.contracts.order.IInStockOrderService;
import ng.saywich.orderservice.application.contracts.order.operations.*;
import ng.saywich.orderservice.application.contracts.order.operations.models.OrderFilter;
import ng.saywich.orderservice.presentation.security.evaluators.InStockOrderSecurityEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("in_stock_orders")
public class InStockOrderController {
    @Autowired
    IInStockOrderService service;

    @Autowired
    InStockOrderSecurityEvaluator orderSecurityEvaluator;

    @GetMapping("/customer")
    @ApiResponse(responseCode = "200", description = "Order list")
    @ApiResponse(responseCode = "400", description = "Invalid data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    public ResponseEntity<List<InStockOrderDto>> getCustomerOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getPrincipal() == null) {
            return ResponseEntity.status(401).build();
        }

        if (!(auth.getPrincipal() instanceof Jwt jwt)) {
            return ResponseEntity.status(401).build();
        }

        String customerId = jwt.getSubject();

        QueryInStockOrder.Request request = new QueryInStockOrder.Request(
            new UUID[0], new OrderFilter(UUID.fromString(customerId), null)
        );

        return ResponseEntity.ok(service.query(request).orders());
    }

    @GetMapping("/manager")
    @ApiResponse(responseCode = "200", description = "Order list")
    @ApiResponse(responseCode = "400", description = "Invalid data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    public ResponseEntity<List<InStockOrderDto>> getManagerOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getPrincipal() == null) {
            return ResponseEntity.status(401).build();
        }

        if (!(auth.getPrincipal() instanceof Jwt jwt)) {
            return ResponseEntity.status(401).build();
        }

        String managerId = jwt.getSubject();

        QueryInStockOrder.Request request = new QueryInStockOrder.Request(
            new UUID[0], new OrderFilter(null, UUID.fromString(managerId))
        );

        return ResponseEntity.ok(service.query(request).orders());
    }

    @GetMapping("/all")
    @ApiResponse(responseCode = "200", description = "Order list")
    @ApiResponse(responseCode = "400", description = "Invalid data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    public ResponseEntity<List<InStockOrderDto>> getOrders(
        QueryInStockOrder.Request request
    ) {
        return ResponseEntity.ok(service.query(request).orders());
    }

    @PostMapping("/")
    @ApiResponse(responseCode = "200", description = "Order created")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Order filter",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = CreateInStockOrder.Request.class)
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
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @ApiResponse(responseCode = "400", description = "Invalid state transition for order")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Order filter",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = AcceptOrder.Request.class)
        )
    )
    @PreAuthorize("@orderSecurityEvaluator.managerIsResponsibleFor(#request.orderId, principal)")
    public ResponseEntity<AcceptOrder.Response> acceptOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody AcceptOrder.Request request
    ) {
        return ResponseEntity.ok(service.acceptOrder(request));
    }

    @PostMapping("/waitForPayment")
    @ApiResponse(responseCode = "200", description = "Order waiting for payment")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @ApiResponse(responseCode = "400", description = "Invalid state transition for order")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Order filter",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = WaitForPaymentOrder.Request.class)
        )
    )
    @PreAuthorize("@orderSecurityEvaluator.managerIsResponsibleFor(#request.orderId, principal)")
    public ResponseEntity<WaitForPaymentOrder.Response> waitForPaymentOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody WaitForPaymentOrder.Request request
    ) {
        return ResponseEntity.ok(service.waitForPaymentOrder(request));
    }

    @PostMapping("/pay")
    @ApiResponse(responseCode = "200", description = "Order paid")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @ApiResponse(responseCode = "400", description = "Invalid state transition for order")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Order filter",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PayOrder.Request.class)
        )
    )
    @PreAuthorize("@orderSecurityEvaluator.managerIsResponsibleFor(#request.orderId, principal)")
    public ResponseEntity<PayOrder.Response> payOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody PayOrder.Request request
    ) {
        return ResponseEntity.ok(service.payOrder(request));
    }

    @PostMapping("/prepare")
    @ApiResponse(responseCode = "200", description = "Order paid")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @ApiResponse(responseCode = "400", description = "Invalid state transition for order")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Order filter",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = PrepareOrder.Request.class)
        )
    )
    @PreAuthorize("@orderSecurityEvaluator.managerIsResponsibleFor(#request.orderId, principal)")
    public ResponseEntity<PrepareOrder.Response> prepareOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody PrepareOrder.Request request
    ) {
        return ResponseEntity.ok(service.prepareOrder(request));
    }

    @PostMapping("/complete")
    @ApiResponse(responseCode = "200", description = "Order completed")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @ApiResponse(responseCode = "400", description = "Invalid state transition for order")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Order filter",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = CompleteOrder.Request.class)
        )
    )
    @PreAuthorize("@orderSecurityEvaluator.managerIsResponsibleFor(#request.orderId, principal)")
    public ResponseEntity<CompleteOrder.Response> completeOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody CompleteOrder.Request request
    ) {
        return ResponseEntity.ok(service.completeOrder(request));
    }

    @PostMapping("/cancel")
    @ApiResponse(responseCode = "200", description = "Order cancelled")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @ApiResponse(responseCode = "400", description = "Invalid state transition for order")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Order filter",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = CancelOrder.Request.class)
        )
    )
    @PreAuthorize("@orderSecurityEvaluator.userIsRelatedTo(#request.orderId, principal)")
    public ResponseEntity<CancelOrder.Response> cancelOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody CancelOrder.Request request
    ) {
        return ResponseEntity.ok(service.cancelOrder(request));
    }
}