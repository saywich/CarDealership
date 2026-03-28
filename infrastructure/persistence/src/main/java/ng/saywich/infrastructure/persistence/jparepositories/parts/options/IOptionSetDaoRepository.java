package ng.saywich.infrastructure.persistence.jparepositories.parts.options;

import ng.saywich.infrastructure.persistence.models.cars.OptionSetDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOptionSetDaoRepository
    extends JpaRepository<OptionSetDao, UUID>, JpaSpecificationExecutor<OptionSetDao> {
}
