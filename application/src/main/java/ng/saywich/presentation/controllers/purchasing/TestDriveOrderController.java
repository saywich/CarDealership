package ng.saywich.presentation.controllers.purchasing;

import ng.saywich.application.contracts.order.ITestDriveOrderService;
import ng.saywich.application.contracts.order.operations.CreateTestDriveOrder;
import ng.saywich.presentation.dtos.purchasing.TestDriveOrderDto;
import ng.saywich.presentation.mappers.purchasing.TestDriveOrderDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test_drive_orders")
public class TestDriveOrderController {
    @Autowired
    ITestDriveOrderService service;

    @Autowired
    TestDriveOrderDtoMapper mapper;

    @PostMapping("/")
    public ResponseEntity<TestDriveOrderDto> createOrder(@RequestBody CreateTestDriveOrder.Request request) {
        return ResponseEntity.ok(mapper.toOrderDto(service.create(request).order()));
    }
}
