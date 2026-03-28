package ng.saywich.infrastructure.persistence.jparepositories.parts;

import ng.saywich.infrastructure.persistence.models.parts.TransmissionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITransmissionDaoRepository
    extends JpaRepository<TransmissionDao, UUID> {
}
