package ng.saywich.application.contracts.car.filtercriterias;

import ng.saywich.domain.car.CarModel;

import java.util.List;

public interface ICriteria {
    public List<CarModel> meetCriteria(List<CarModel> models);
}
