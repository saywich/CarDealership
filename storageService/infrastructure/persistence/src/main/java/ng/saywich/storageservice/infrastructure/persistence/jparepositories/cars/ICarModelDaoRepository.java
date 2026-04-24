package ng.saywich.storageservice.infrastructure.persistence.jparepositories.cars;

import ng.saywich.storageservice.infrastructure.persistence.models.cars.CarModelDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICarModelDaoRepository
    extends JpaRepository<CarModelDao, UUID>,
            JpaSpecificationExecutor<CarModelDao>,
            PagingAndSortingRepository<CarModelDao, UUID>
{ }
