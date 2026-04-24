package ng.saywich.storageservice.infrastructure.persistence.jparepositories.stock;

import ng.saywich.storageservice.infrastructure.persistence.models.stock.StockPartReservationDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IStockPartReservationDaoRepository extends JpaRepository<StockPartReservationDao, UUID> {
    List<StockPartReservationDao> findAllByPartStockUnit_PartId(UUID partId);
    List<StockPartReservationDao> findAllByAssembleRequest_Id(UUID requestId);
}