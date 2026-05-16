package ng.saywich.orderservice.presentation.controllers.carstock;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ng.saywich.orderservice.application.abstractions.carstock.IStockCarRepository;
import ng.saywich.orderservice.application.contracts.dtos.carstock.StockCarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cars_in_stock")
public class StockCarController {
    @Autowired
    IStockCarRepository repository;

    @GetMapping("/")
    @ApiResponse(responseCode = "200", description = "Order list")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "No permission")
    public ResponseEntity<List<StockCarDto>> getStockCars() {
        return ResponseEntity.ok(repository.queryInStock());
    }
}
