package ng.saywich.orderservice.presentation.controllers.purchasing;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ng.saywich.orderservice.application.contracts.dtos.purchasing.ManufactureOrderDto;
import ng.saywich.orderservice.application.contracts.order.IManufactureOrderService;
import ng.saywich.orderservice.application.contracts.order.operations.*;
import ng.saywich.orderservice.presentation.security.evaluators.ManufactureOrderSecurityEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manufacture_orders")
public class ManufactureOrderController {
    @Autowired
    IManufactureOrderService service;

    @Autowired
    ManufactureOrderSecurityEvaluator orderSecurityEvaluator;

    @GetMapping("/customer")
    @ApiResponse(responseCode = "200", description = "Order list")
    @ApiResponse(responseCode = "400", description = "Invalid data")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Order filter",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = QueryManufactureOrder.Request.class)
        )
    )
    public ResponseEntity<List<ManufactureOrderDto>> getCustomerOrders(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody QueryManufactureOrder.Request request
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
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Order filter",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = QueryManufactureOrder.Request.class)
        )
    )
    public ResponseEntity<List<ManufactureOrderDto>> getManagerOrders(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody QueryManufactureOrder.Request request
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
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "Order filter",
        required = true,
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = QueryManufactureOrder.Request.class)
        )
    )
    public ResponseEntity<List<ManufactureOrderDto>> getOrders(
        @RequestBody QueryManufactureOrder.Request request
    ) {
        return ResponseEntity.ok(service.query(request).orders());
    }

    @PostMapping("/")
    public ResponseEntity<ManufactureOrderDto> createOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody CreateManufactureOrder.Request request
    ) {
        return ResponseEntity.ok(service.create(request).order());
    }

    @PostMapping("/accept")
    @PreAuthorize("@orderSecurityEvaluator.managerIsResponsibleFor(#request.orderId, principal)")
    public ResponseEntity<AcceptOrder.Response> acceptOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody AcceptOrder.Request request
    ) {
        return ResponseEntity.ok(service.acceptOrder(request));
    }

    @PostMapping("/waitForPayment")
    @PreAuthorize("@orderSecurityEvaluator.managerIsResponsibleFor(#request.orderId, principal)")
    public ResponseEntity<WaitForPaymentOrder.Response> waitForPaymentOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody WaitForPaymentOrder.Request request
    ) {
        return ResponseEntity.ok(service.waitForPaymentOrder(request));
    }

    @PostMapping("/pay")
    @PreAuthorize("@orderSecurityEvaluator.managerIsResponsibleFor(#request.orderId, principal)")
    public ResponseEntity<PayOrder.Response> payOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody PayOrder.Request request
    ) {
        return ResponseEntity.ok(service.payOrder(request));
    }

    @PostMapping("/wait_for_delivery")
    @PreAuthorize("@orderSecurityEvaluator.managerIsResponsibleFor(#request.orderId, principal)")
    public ResponseEntity<WaitForDeliveryOrder.Response> waitForDeliveryOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody WaitForDeliveryOrder.Request request
    ) {
        return ResponseEntity.ok(service.waitForDeliveryOrder(request));
    }

    @PostMapping("/deliver_car")
    @PreAuthorize("@orderSecurityEvaluator.managerIsResponsibleFor(#request.orderId, principal)")
    public ResponseEntity<DeliverCarOrder.Response> deliverOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody DeliverCarOrder.Request request
    ) {
        return ResponseEntity.ok(service.deliverCarOrder(request));
    }

    @PostMapping("/complete")
    @PreAuthorize("@orderSecurityEvaluator.managerIsResponsibleFor(#request.orderId, principal)")
    public ResponseEntity<CompleteOrder.Response> completeOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody CompleteOrder.Request request
    ) {
        return ResponseEntity.ok(service.completeOrder(request));
    }

    @PostMapping("/cancel")
    @PreAuthorize("@orderSecurityEvaluator.userIsRelatedTo(#request.orderId, principal)")
    public ResponseEntity<CancelOrder.Response> cancelOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody CancelOrder.Request request
    ) {
        return ResponseEntity.ok(service.cancelOrder(request));
    }
}
