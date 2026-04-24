package ng.saywich.storageservice.infrastructure.persistence.jparepositories.stock;

import ng.saywich.storageservice.infrastructure.persistence.models.stock.PartStockUnitDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IPartStockUnitDaoRepository extends JpaRepository<PartStockUnitDao, UUID> {
    Optional<PartStockUnitDao> findByPartId(UUID partId);
}
