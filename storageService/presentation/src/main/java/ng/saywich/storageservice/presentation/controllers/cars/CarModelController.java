package ng.saywich.storageservice.presentation.controllers.cars;

import ng.saywich.storageservice.application.contracts.car.ICarModelService;
import ng.saywich.storageservice.application.contracts.car.operations.*;
import ng.saywich.storageservice.application.contracts.dtos.cars.CarModelDto;
import ng.saywich.storageservice.application.contracts.dtos.cars.CarModelFilterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("car_models")
public class CarModelController {
    @Autowired
    ICarModelService carModelService;

    @GetMapping("/")
    public ResponseEntity<List<CarModelDto>> getModels(int pageSize, int page) {
        List<CarModelDto> carModelDtoList = carModelService.queryPage(
                new QueryCarModels.Request(new UUID[0]),
                pageSize,
                page
            ).models();

        if (carModelDtoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(carModelDtoList);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CarModelDto>> filterModels(
        CarModelFilterDto filterDto, int pageSize, int page
    ) {
        List<CarModelDto> carModelDtoList
            = carModelService.queryWithFilter(
                new FilterCarModels.Request(filterDto),
                pageSize,
                page
            ).models();

        if (carModelDtoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(carModelDtoList);
    }

    @PostMapping("/")
    public ResponseEntity<CarModelDto> createCarModel(@RequestBody CreateCarModel.Request request) {
        return ResponseEntity.ok(carModelService.create(request).model());
    }

    @PutMapping("/")
    public ResponseEntity<CarModelDto> updateCarModel(@RequestBody UpdateCarModel.Request request) {
        return ResponseEntity.ok(
            carModelService.update(request).model()
        );
    }

    @DeleteMapping("/")
    public ResponseEntity<CarModelDto> deleteCarModel(@RequestBody DeleteCarModel.Request request) {
        return ResponseEntity.ok(
            carModelService.delete(request).model()
        );
    }
}
