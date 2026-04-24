package ng.saywich.orderservice.presentation.controllers.purchasing;

import ng.saywich.orderservice.application.contracts.dtos.purchasing.TestDriveOrderDto;
import ng.saywich.orderservice.application.contracts.order.ITestDriveOrderService;
import ng.saywich.orderservice.application.contracts.order.operations.CreateTestDriveOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test_drive_orders")
public class TestDriveOrderController {
    @Autowired
    ITestDriveOrderService service;

    @PostMapping("/")
    public ResponseEntity<TestDriveOrderDto> createOrder(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody CreateTestDriveOrder.Request request
    ) {
        return ResponseEntity.ok(service.create(request).order());
    }
}
