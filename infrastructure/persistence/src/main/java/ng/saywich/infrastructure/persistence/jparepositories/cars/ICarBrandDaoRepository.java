package ng.saywich.infrastructure.persistence.jparepositories.cars;

import ng.saywich.infrastructure.persistence.models.cars.CarBrandDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICarBrandDaoRepository
    extends JpaRepository<CarBrandDao, UUID>
{ }
