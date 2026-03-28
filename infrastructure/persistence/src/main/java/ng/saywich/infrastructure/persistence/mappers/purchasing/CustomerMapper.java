package ng.saywich.infrastructure.persistence.mappers.purchasing;

import ng.saywich.domain.purchasing.Customer;
import ng.saywich.infrastructure.persistence.models.purchasing.CustomerDao;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    @Mapping(target = "id", source = "customer.customerId")
    public CustomerDao toCustomerDao(Customer customer);

    @Mapping(target = "customerId", source = "dao.id")
    public Customer toCustomer(CustomerDao dao);
}
