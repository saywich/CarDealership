package ng.saywich.infrastructure.persistence.jparepositories.parts;

import ng.saywich.infrastructure.persistence.models.parts.EngineDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEngineDaoRepository
    extends JpaRepository<EngineDao, UUID> {
}

