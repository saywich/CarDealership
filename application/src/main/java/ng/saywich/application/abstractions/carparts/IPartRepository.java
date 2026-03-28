package ng.saywich.application.abstractions.carparts;

import ng.saywich.domain.value.Price;

import java.util.List;
import java.util.UUID;

public interface IPartRepository<T> {
    public void create(T part);

    public List<T> query(UUID[] partIds);

    public void update(T part);

    public void delete(T part);
}
