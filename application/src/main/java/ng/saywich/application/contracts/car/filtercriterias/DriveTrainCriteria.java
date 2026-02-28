package ng.saywich.application.contracts.car.filtercriterias;

import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.car.enums.DriveTrain;

import java.util.List;

public record DriveTrainCriteria(DriveTrain driveTrain) implements ICriteria {

    @Override
    public List<CarModel> meetCriteria(List<CarModel> models) {
        return models.stream()
            .filter(m -> m.getTransmission().carPart().driveTrain() == driveTrain)
            .toList();
    }
}
