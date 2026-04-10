package ng.saywich.infrastructure.persistence.jparepositories.purchasing;

import ng.saywich.infrastructure.persistence.models.purchasing.InStockOrderDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IInStockOrderDaoRepository
    extends JpaRepository<InStockOrderDao, UUID>,
            JpaSpecificationExecutor<InStockOrderDao>
{ }
