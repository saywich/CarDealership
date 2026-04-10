package ng.saywich.presentation.controllers.cars;

import ng.saywich.application.contracts.car.operations.CreateCarBrand;
import ng.saywich.application.contracts.car.operations.QueryCarBrands;
import ng.saywich.application.contracts.dtos.cars.CarBrandDto;
import ng.saywich.application.services.car.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("car_brands")
public class CarBrandController {
    @Autowired
    private CarBrandService carBrandService;

    @GetMapping("/")
    public ResponseEntity<List<CarBrandDto>> getBrands() {
        List<CarBrandDto> brandDtoList =
            carBrandService.query(new QueryCarBrands.Request(new UUID[0])).brands();

        if (brandDtoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(brandDtoList);
    }

    @PostMapping("/")
    public ResponseEntity<CarBrandDto> createBrand(
        @RequestBody CreateCarBrand.Request request
    ) {
        return ResponseEntity.ok(carBrandService.create(request).brand());
    }
}
