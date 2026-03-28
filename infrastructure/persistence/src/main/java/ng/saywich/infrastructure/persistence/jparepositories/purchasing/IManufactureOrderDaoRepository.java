package ng.saywich.infrastructure.persistence.jparepositories.purchasing;

import ng.saywich.infrastructure.persistence.models.purchasing.ManufactureOrderDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IManufactureOrderDaoRepository extends JpaRepository<ManufactureOrderDao, UUID> {
}
