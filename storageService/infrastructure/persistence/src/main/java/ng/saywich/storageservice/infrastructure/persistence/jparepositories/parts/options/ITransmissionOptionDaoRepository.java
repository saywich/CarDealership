package ng.saywich.storageservice.infrastructure.persistence.jparepositories.parts.options;

import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.TransmissionOptionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ITransmissionOptionDaoRepository
    extends JpaRepository<TransmissionOptionDao, UUID>
{
    @Query("SELECT o FROM TransmissionOptionDao AS o WHERE o.modelId = ?1")
    public List<TransmissionOptionDao> findByModelId(UUID modelId);
}
