package ng.saywich.application.contracts.car.filtercriterias;

import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.car.enums.TransmissionType;

import java.util.List;

public record TransmissionTypeCriteria(
    TransmissionType transmissionType
) implements ICriteria {
    @Override
    public List<CarModel> meetCriteria(List<CarModel> models) {
        return models.stream()
            .filter(m -> m.getTransmission().carPart().type() == transmissionType)
            .toList();
    }
}
