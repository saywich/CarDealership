package ng.saywich.application.services.car;

import ng.saywich.application.abstractions.car.ICarBrandRepository;
import ng.saywich.application.contracts.car.ICarBrandService;
import ng.saywich.application.contracts.car.operations.CreateCarBrand;
import ng.saywich.application.contracts.car.operations.QueryCarBrands;
import ng.saywich.domain.car.CarBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarBrandService implements ICarBrandService {
    @Autowired
    private ICarBrandRepository carBrandRepository;


    @Override
    public QueryCarBrands.Response query(QueryCarBrands.Request request) {
        return new QueryCarBrands.Response(
            carBrandRepository.query(request.ids()));
    }

    @Override
    public CreateCarBrand.Response create(CreateCarBrand.Request request) {
        CarBrand brand = new CarBrand(UUID.randomUUID(), request.name());
        carBrandRepository.create(brand);

        return new CreateCarBrand.Response(brand);
    }
}
