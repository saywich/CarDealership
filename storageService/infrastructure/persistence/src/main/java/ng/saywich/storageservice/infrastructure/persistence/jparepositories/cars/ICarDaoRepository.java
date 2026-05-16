package ng.saywich.storageservice.infrastructure.persistence.jparepositories.cars;

import ng.saywich.storageservice.infrastructure.persistence.models.cars.CarDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ICarDaoRepository
    extends JpaRepository<CarDao, UUID>,
            PagingAndSortingRepository<CarDao, UUID>
{
    @Query("""
        SELECT c FROM CarDao c
        WHERE NOT EXISTS (
            SELECT r FROM StockCarReservationDao r
            WHERE r.reservedCar = c
        )
    """)
    List<CarDao> findInStock();
}
