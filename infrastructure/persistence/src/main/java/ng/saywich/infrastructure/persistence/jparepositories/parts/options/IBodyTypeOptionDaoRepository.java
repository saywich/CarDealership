package ng.saywich.infrastructure.persistence.jparepositories.parts.options;

import ng.saywich.infrastructure.persistence.models.parts.options.BodyTypeOptionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IBodyTypeOptionDaoRepository
    extends JpaRepository<BodyTypeOptionDao, UUID>
{
    @Query("SELECT o FROM BodyTypeOptionDao AS o WHERE o.modelId = ?1")
    public List<BodyTypeOptionDao> findByModelId(UUID modelId);
}
