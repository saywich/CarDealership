package ng.saywich.application.abstractions.order;

import ng.saywich.domain.purchasing.Manager;

import java.util.List;
import java.util.UUID;

public interface IManagerRepository {
    List<Manager> query(UUID[] ids);

    Manager create(Manager manager);

    Manager update(Manager manager);

    Manager delete(UUID id);
}
