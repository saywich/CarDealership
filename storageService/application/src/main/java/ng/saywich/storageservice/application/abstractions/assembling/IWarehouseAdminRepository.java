package ng.saywich.storageservice.application.abstractions.assembling;

import ng.saywich.storageservice.domain.assembling.WarehouseAdmin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IWarehouseAdminRepository {
    List<WarehouseAdmin> query(UUID[] adminIds);

    Optional<WarehouseAdmin> queryById(UUID adminId);

    void create(WarehouseAdmin admin);

    void update(WarehouseAdmin admin);

    void delete(UUID adminId);
}
