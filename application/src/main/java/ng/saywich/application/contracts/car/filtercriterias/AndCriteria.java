package ng.saywich.application.contracts.car.filtercriterias;

import lombok.AllArgsConstructor;
import ng.saywich.domain.car.CarModel;

import java.util.List;

@AllArgsConstructor
public class AndCriteria implements ICriteria {
    private ICriteria left;

    private ICriteria right;

    @Override
    public List<CarModel> meetCriteria(List<CarModel> models) {
        List<CarModel> leftFiltered = left.meetCriteria(models);

        return right.meetCriteria(leftFiltered);
    }
}
