package ng.saywich.storageservice.application.abstractions.stock;

import ng.saywich.storageservice.domain.stock.PartStockUnit;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPartStockUnitRepository {
    List<PartStockUnit> query(UUID[] unitIds);

    Optional<PartStockUnit> queryById(UUID unitId);

    Optional<PartStockUnit> queryByPartId(UUID partId);

    void create(PartStockUnit unit);

    void update(PartStockUnit unit);

    void delete(UUID unitId);

    boolean isPartAvailable(UUID partId);
}
