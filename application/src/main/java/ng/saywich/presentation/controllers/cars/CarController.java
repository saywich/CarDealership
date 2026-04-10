package ng.saywich.presentation.controllers.cars;

import ng.saywich.application.contracts.car.ICarService;
import ng.saywich.application.contracts.car.operations.*;
import ng.saywich.application.contracts.dtos.cars.CarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("cars")
public class CarController {
    @Autowired
    ICarService carService;

    @GetMapping("/")
    public ResponseEntity<List<CarDto>> getCars(int page, int pageSize) {
        List<CarDto> carDtoList = carService.queryPage(
            new QueryCars.Request(new UUID[0]), pageSize, page).cars();

        if (carDtoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(carDtoList);
    }

    @PostMapping("/")
    public ResponseEntity<CarDto> createCar(@RequestBody CreateCar.Request request) {
        return ResponseEntity.ok(carService.create(request).car()
        );
    }

    @PutMapping("/")
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto) {
        return ResponseEntity.ok(
            carService.update(new UpdateCar.Request(carDto)).car()
        );
    }

    @DeleteMapping("/")
    public ResponseEntity<CarDto> deleteCar(@RequestBody CarDto carDto) {
        return ResponseEntity.ok(
            carService.delete(new DeleteCar.Request(carDto.carId())).car()
        );
    }
}
