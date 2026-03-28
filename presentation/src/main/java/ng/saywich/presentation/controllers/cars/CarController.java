package ng.saywich.presentation.controllers.cars;

import ng.saywich.application.contracts.car.ICarModelService;
import ng.saywich.application.contracts.car.ICarService;
import ng.saywich.application.contracts.car.operations.*;
import ng.saywich.domain.car.Car;
import ng.saywich.domain.car.CarModel;
import ng.saywich.presentation.dtos.cars.CarDto;
import ng.saywich.presentation.dtos.cars.CarModelDto;
import ng.saywich.presentation.dtos.cars.CarModelFilterDto;
import ng.saywich.presentation.mappers.cars.CarDtoMapper;
import ng.saywich.presentation.mappers.cars.CarModelDtoMapper;
import ng.saywich.presentation.mappers.cars.CarModelFilterDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("cars")
public class CarController {
    @Autowired
    ICarService carService;

    @Autowired
    CarDtoMapper carDtoMapper;

    @GetMapping("/")
    public List<CarDto> getCars() {
        return carService.query(new QueryCars.Request(new UUID[0]))
            .cars().stream()
            .map(carDtoMapper::toCarDto)
            .toList();
    }

    @PostMapping("/")
    public CarDto createCar(@RequestBody CarDto carDto) {
        Car car = carDtoMapper.toCar(carDto);

        return carDtoMapper.toCarDto(
            carService.create(
                new CreateCar.Request(
                    car.vinNumber(),
                    car.model().getName(),
                    car.model().getBrand(),
                    car.model().getOptionSet().engine(),
                    car.model().getOptionSet().transmission(),
                    car.model().getOptionSet().bodyType(),
                    car.model().getOptionSet().rims(),
                    car.model().getOptionSet().interior(),
                    car.model().getOptionSet().color(),
                    car.model().getBasePrice()
                )
            ).car()
        );
    }

    @PutMapping("/")
    public CarDto updateCar(@RequestBody CarDto carDto) {
        Car car = carDtoMapper.toCar(carDto);

        return carDtoMapper.toCarDto(
            carService.update(new UpdateCar.Request(car)).car()
        );
    }

    @DeleteMapping("/")
    public CarDto deleteCar(@RequestBody CarDto carDto) {
        Car car = carDtoMapper.toCar(carDto);

        return carDtoMapper.toCarDto(
            carService.delete(new DeleteCar.Request(car.carId())).car()
        );
    }
}
