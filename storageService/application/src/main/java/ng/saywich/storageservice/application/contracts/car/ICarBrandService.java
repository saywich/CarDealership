package ng.saywich.storageservice.application.contracts.car;

import ng.saywich.storageservice.application.contracts.car.operations.CreateCarBrand;
import ng.saywich.storageservice.application.contracts.car.operations.QueryCarBrands;

public interface ICarBrandService {
    QueryCarBrands.Response query(QueryCarBrands.Request request);

    CreateCarBrand.Response create(CreateCarBrand.Request request);
}
