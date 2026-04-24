package ng.saywich.storageservice.infrastructure.persistence.jparepositories.cars;

import ng.saywich.storageservice.infrastructure.persistence.models.cars.CarDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICarDaoRepository
    extends JpaRepository<CarDao, UUID>,
            PagingAndSortingRepository<CarDao, UUID>
{ }
