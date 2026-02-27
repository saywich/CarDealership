package ng.saywich.application.abstractions.carparts;

import ng.saywich.domain.value.Price;

import java.util.List;
import java.util.UUID;

public interface IPartRepository<T> {
    public void create(T part);

    public List<T> query(UUID[] partIds);

    public List<T> queryForModel(UUID modelId);

    public void update(T engine);

    public void delete(UUID partId);

    public void linkToModel(UUID modelId, UUID partId, Price priceDifference);
}
