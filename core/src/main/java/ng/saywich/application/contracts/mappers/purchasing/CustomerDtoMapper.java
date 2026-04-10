package ng.saywich.application.contracts.mappers.purchasing;

import ng.saywich.domain.purchasing.Customer;
import ng.saywich.application.contracts.dtos.purchasing.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerDtoMapper {
    Customer toCustomer(CustomerDto dto);

    CustomerDto toCustomerDto(Customer customer);
}
