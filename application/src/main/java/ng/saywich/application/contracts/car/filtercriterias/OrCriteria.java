package ng.saywich.application.contracts.car.filtercriterias;

import lombok.AllArgsConstructor;
import ng.saywich.domain.car.CarModel;

import java.util.List;

@AllArgsConstructor
public class OrCriteria implements ICriteria {
    private ICriteria left;

    private ICriteria right;
    @Override
    public List<CarModel> meetCriteria(List<CarModel> models) {
        List<CarModel> leftFiltered = left.meetCriteria(models);
        List<CarModel> rightFiltered = right.meetCriteria(models);

        for (CarModel model : rightFiltered) {
            if (!leftFiltered.contains(model)) {
                leftFiltered.add(model);
            }
        }

        return leftFiltered;
    }
}
