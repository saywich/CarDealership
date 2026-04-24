package ng.saywich.orderservice.application.contracts.mappers.purchasing;

import ng.saywich.orderservice.application.contracts.dtos.purchasing.ManagerDto;
import ng.saywich.shared.domain.purchasing.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ManagerDtoMapper {
    Manager toManager(ManagerDto dto);

    ManagerDto toManagerDto(Manager manager);
}
