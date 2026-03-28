package ng.saywich.application.abstractions.carparts;

import ng.saywich.domain.car.CarOption;

import java.util.List;
import java.util.UUID;

public interface IPartOptionRepository<T> {
    public void create(CarOption<T> option);

    public List<CarOption<T>> query(UUID[] partIds);

    public List<CarOption<T>> queryForModel(UUID modelId);

    public void update(CarOption<T> option);

    public void delete(CarOption<T> option);
}
