package ng.saywich.storageservice.infrastructure.persistence.jparepositories.assembling;

import ng.saywich.storageservice.infrastructure.persistence.models.assembling.AssembleRequestDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAssembleRequestDaoRepository
    extends JpaRepository<AssembleRequestDao, UUID>
{ }
