package ng.saywich.presentation.controllers.cars;

import ng.saywich.application.contracts.car.ICarModelService;
import ng.saywich.application.contracts.car.operations.*;
import ng.saywich.domain.car.CarModel;
import ng.saywich.presentation.dtos.cars.CarModelDto;
import ng.saywich.presentation.dtos.cars.CarModelFilterDto;
import ng.saywich.presentation.mappers.cars.CarModelDtoMapper;
import ng.saywich.presentation.mappers.cars.CarModelFilterDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("car_models")
public class CarModelController {
    @Autowired
    ICarModelService carModelService;

    @Autowired
    CarModelDtoMapper carModelMapper;

    @Autowired
    CarModelFilterDtoMapper filterMapper;

    @GetMapping("/")
    public List<CarModelDto> getModels() {
        return carModelService.query(new QueryCarModels.Request(new UUID[0]))
            .models().stream()
            .map(m -> carModelMapper.toCarModelDto(m))
            .toList();
    }

    @GetMapping("/filter")
    public List<CarModelDto> filterModels(CarModelFilterDto filterDto) {
        return carModelService.queryWithFilter(
            new FilterCarModels.Request(filterMapper.toFilter(filterDto)))
            .models().stream()
            .map(m -> carModelMapper.toCarModelDto(m))
            .toList();
    }

    @PostMapping("/")
    public CarModelDto createCarModel(@RequestBody CreateCarModel.Request request) {
        return carModelMapper.toCarModelDto(
            carModelService.create(request).model()
        );
    }

    @PutMapping("/")
    public CarModelDto updateCarModel(@RequestBody CarModelDto carModelDto) {
        CarModel model = carModelMapper.toCarModel(carModelDto);

        return carModelMapper.toCarModelDto(
            carModelService.update(new UpdateCarModel.Request(model)).model()
        );
    }

    @DeleteMapping("/")
    public CarModelDto deleteCarModel(@RequestBody CarModelDto carModelDto) {
        CarModel model = carModelMapper.toCarModel(carModelDto);

        return carModelMapper.toCarModelDto(
            carModelService.delete(new DeleteCarModel.Request(model.getModelId())).model()
        );
    }
}
