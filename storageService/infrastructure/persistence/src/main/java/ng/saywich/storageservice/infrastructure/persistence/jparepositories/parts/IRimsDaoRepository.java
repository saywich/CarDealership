package ng.saywich.storageservice.infrastructure.persistence.jparepositories.parts;

import ng.saywich.storageservice.infrastructure.persistence.models.parts.RimsDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRimsDaoRepository
    extends JpaRepository<RimsDao, UUID> {
}
