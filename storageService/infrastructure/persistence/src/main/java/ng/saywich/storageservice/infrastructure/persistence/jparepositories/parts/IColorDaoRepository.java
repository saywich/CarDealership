package ng.saywich.storageservice.infrastructure.persistence.jparepositories.parts;

import ng.saywich.storageservice.infrastructure.persistence.models.parts.ColorDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IColorDaoRepository
    extends JpaRepository<ColorDao, UUID> {
}
