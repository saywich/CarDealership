package ng.saywich.storageservice.infrastructure.persistence.jparepositories.parts.options;

import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.ColorOptionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IColorOptionDaoRepository
    extends JpaRepository<ColorOptionDao, UUID>
{
    @Query("SELECT o FROM ColorOptionDao AS o WHERE o.modelId = ?1")
    public List<ColorOptionDao> findByModelId(UUID modelId);
}
