package ng.saywich.storageservice.infrastructure.persistence.jparepositories.stock;

import ng.saywich.storageservice.infrastructure.persistence.models.stock.StockCarReservationDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IStockCarReservationDaoRepository extends JpaRepository<StockCarReservationDao, UUID> {
    Optional<StockCarReservationDao> findByReservedCar_Id(UUID carId);
    Optional<StockCarReservationDao> findByAssembleRequest_Id(UUID requestId);
}