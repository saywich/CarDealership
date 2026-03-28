package ng.saywich.presentation.controllers.cars;

import ng.saywich.application.contracts.car.ICarModelService;
import ng.saywich.application.contracts.car.operations.*;
import ng.saywich.presentation.dtos.cars.CarModelDto;
import ng.saywich.presentation.dtos.cars.CarModelFilterDto;
import ng.saywich.presentation.mappers.cars.CarModelDtoMapper;
import ng.saywich.presentation.mappers.cars.CarModelFilterDtoMapper;
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
    public ResponseEntity<CarModelDto> updateCarModel(@RequestBody CarModelDto carModelDto) {
        return ResponseEntity.ok(
            carModelService.update(new UpdateCarModel.Request(carModelDto)).model()
        );
    }

    @DeleteMapping("/")
    public ResponseEntity<CarModelDto> deleteCarModel(@RequestBody CarModelDto carModelDto) {
        return ResponseEntity.ok(
            carModelService.delete(new DeleteCarModel.Request(carModelDto.modelId())).model()
        );
    }
}
