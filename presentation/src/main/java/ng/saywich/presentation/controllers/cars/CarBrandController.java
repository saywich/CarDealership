package ng.saywich.presentation.controllers.cars;

import ng.saywich.application.contracts.car.operations.CreateCarBrand;
import ng.saywich.application.contracts.car.operations.QueryCarBrands;
import ng.saywich.application.services.car.CarBrandService;
import ng.saywich.infrastructure.persistence.models.cars.CarBrandDao;
import ng.saywich.presentation.dtos.cars.CarBrandDto;
import ng.saywich.presentation.dtos.cars.CarModelDto;
import ng.saywich.presentation.mappers.cars.CarBrandDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("car_brands")
public class CarBrandController {
    @Autowired
    private CarBrandService carBrandService;

    @Autowired
    private CarBrandDtoMapper mapper;

    @GetMapping("/")
    public List<CarBrandDto> getBrands() {
        return carBrandService.query(new QueryCarBrands.Request(new UUID[0]))
            .brands().stream()
            .map(mapper::toCarBrandDto)
            .toList();
    }

    @PostMapping("/")
    public CarBrandDto createBrand(@RequestBody CreateCarBrand.Request request) {
        return mapper.toCarBrandDto(carBrandService.create(request).brand());
    }
}
