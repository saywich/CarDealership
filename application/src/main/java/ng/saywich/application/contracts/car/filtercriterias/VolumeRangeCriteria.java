package ng.saywich.application.contracts.car.filtercriterias;

import ng.saywich.application.value.Range;
import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.value.HorsePower;
import ng.saywich.domain.value.Volume;

import java.util.List;

public record VolumeRangeCriteria(Range<Volume> volumeRange) implements ICriteria {
    @Override
    public List<CarModel> meetCriteria(List<CarModel> models) {
        return models.stream()
            .filter(m -> {
                Volume modelVolume = m.getEngine().carPart().volume();

                return modelVolume.value() >= volumeRange.start().value() &&
                    modelVolume.value() <= volumeRange.end().value();
            })
            .toList();
    }
}
