package ng.saywich.orderservice.application.contracts.mappers.purchasing;

import ng.saywich.orderservice.application.contracts.dtos.purchasing.CustomerDto;
import ng.saywich.shared.domain.purchasing.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerDtoMapper {
    Customer toCustomer(CustomerDto dto);

    CustomerDto toCustomerDto(Customer customer);
}
