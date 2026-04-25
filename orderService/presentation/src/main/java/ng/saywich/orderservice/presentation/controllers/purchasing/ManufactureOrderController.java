package ng.saywich.orderservice.presentation.controllers.purchasing;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ng.saywich.orderservice.application.contracts.dtos.purchasing.ManufactureOrderDto;
import ng.saywich.orderservice.application.contracts.order.IManufactureOrderService;
import ng.saywich.orderservice.application.contracts.order.operations.*;
import ng.saywich.orderservice.application.contracts.order.operations.models.OrderFilter;
import ng.saywich.orderservice.presentation.security.evaluators.ManufactureOrderSecurityEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("manufacture_orders")
public class ManufactureOrderController {
    @Autowired
    IManufactureOrderService service;

    @Autowired
    ManufactureOrderSecurityEvaluator orderSecurityEvaluator;

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
    public ResponseEntity<List<ManufactureOrderDto>> getOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getPrincipal() == null) {
            return ResponseEntity.status(401).build();
        }

        if (!(auth.getPrincipal() instanceof Jwt jwt)) {
            return ResponseEntity.status(401).build();
        }

        for (GrantedAuthority authority : auth.getAuthorities()) {
            if (authority.getAuthority() == "ROLE_user") {
                return ResponseEntity.ok(service.query(new QueryManufactureOrder.Request(
                    new UUID[0], new OrderFilter(UUID.fromString(jwt.getSubject()), null)
                )).orders());
            }
        }

        return ResponseEntity.ok(service.query(new QueryManufactureOrder.Request(
            new UUID[0], null
        )).orders());
    }

    @PostMapping("/")
    public ResponseEntity<ManufactureOrderDto> createOrder(
        @RequestBody CreateManufactureOrder.Request request
    ) {
        return ResponseEntity.ok(service.create(request).order());
    }

    @PostMapping("/accept")
    @PreAuthorize("@orderSecurityEvaluator.managerIsResponsibleFor(#request.orderId, principal)")
    public ResponseEntity<AcceptOrder.Response> acceptOrder(
        @RequestBody AcceptOrder.Request request
    ) {
        return ResponseEntity.ok(service.acceptOrder(request));
    }

    @PostMapping("/waitForPayment")
    @PreAuthorize("@orderSecurityEvaluator.managerIsResponsibleFor(#request.orderId, principal)")
    public ResponseEntity<WaitForPaymentOrder.Response> waitForPaymentOrder(
        @RequestBody WaitForPaymentOrder.Request request
    ) {
        return ResponseEntity.ok(service.waitForPaymentOrder(request));
    }

    @PostMapping("/pay")
    @PreAuthorize("@orderSecurityEvaluator.managerIsResponsibleFor(#request.orderId, principal)")
    public ResponseEntity<PayOrder.Response> payOrder(
        @RequestBody PayOrder.Request request
    ) {
        return ResponseEntity.ok(service.payOrder(request));
    }

    @PostMapping("/wait_for_delivery")
    @PreAuthorize("@orderSecurityEvaluator.managerIsResponsibleFor(#request.orderId, principal)")
    public ResponseEntity<WaitForDeliveryOrder.Response> waitForDeliveryOrder(
        @RequestBody WaitForDeliveryOrder.Request request
    ) {
        return ResponseEntity.ok(service.waitForDeliveryOrder(request));
    }

    @PostMapping("/deliver_car")
    @PreAuthorize("@orderSecurityEvaluator.managerIsResponsibleFor(#request.orderId, principal)")
    public ResponseEntity<DeliverCarOrder.Response> deliverOrder(
        @RequestBody DeliverCarOrder.Request request
    ) {
        return ResponseEntity.ok(service.deliverCarOrder(request));
    }

    @PostMapping("/complete")
    @PreAuthorize("@orderSecurityEvaluator.managerIsResponsibleFor(#request.orderId, principal)")
    public ResponseEntity<CompleteOrder.Response> completeOrder(
        @RequestBody CompleteOrder.Request request
    ) {
        return ResponseEntity.ok(service.completeOrder(request));
    }

    @PostMapping("/cancel")
    @PreAuthorize("@orderSecurityEvaluator.userIsRelatedTo(#request.orderId, principal)")
    public ResponseEntity<CancelOrder.Response> cancelOrder(
        @RequestBody CancelOrder.Request request
    ) {
        return ResponseEntity.ok(service.cancelOrder(request));
    }
}
