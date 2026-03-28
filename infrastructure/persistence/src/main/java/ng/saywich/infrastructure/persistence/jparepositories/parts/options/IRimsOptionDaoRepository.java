package ng.saywich.infrastructure.persistence.jparepositories.parts.options;

import ng.saywich.infrastructure.persistence.models.parts.options.RimsOptionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IRimsOptionDaoRepository
    extends JpaRepository<RimsOptionDao, UUID>
{
    @Query("SELECT o FROM RimsOptionDao AS o WHERE o.modelId = ?1")
    public List<RimsOptionDao> findByModelId(UUID modelId);
}
