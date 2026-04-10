package ng.saywich.application.contracts.mappers.purchasing;

import ng.saywich.domain.purchasing.Manager;
import ng.saywich.application.contracts.dtos.purchasing.ManagerDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ManagerDtoMapper {
    Manager toManager(ManagerDto dto);

    ManagerDto toManagerDto(Manager manager);
}
