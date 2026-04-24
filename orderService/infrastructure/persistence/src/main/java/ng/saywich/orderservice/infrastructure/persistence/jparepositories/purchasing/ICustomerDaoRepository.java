package ng.saywich.orderservice.infrastructure.persistence.jparepositories.purchasing;

import ng.saywich.orderservice.infrastructure.persistence.models.purchasing.CustomerDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICustomerDaoRepository
    extends JpaRepository<CustomerDao, UUID> {
}
