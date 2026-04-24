package ng.saywich.storageservice.infrastructure.persistence.jparepositories.parts.options;

import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.EngineOptionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IEngineOptionDaoRepository
    extends JpaRepository<EngineOptionDao, UUID>
{
    @Query("SELECT o FROM EngineOptionDao AS o WHERE o.modelId = ?1")
    public List<EngineOptionDao> findByModelId(UUID modelId);
}
