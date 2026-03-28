package ng.saywich.presentation.mappers.purchasing;

import ng.saywich.domain.purchasing.Manager;
import ng.saywich.presentation.dtos.purchasing.ManagerDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ManagerDtoMapper {
    Manager toManager(ManagerDto dto);

    ManagerDto toManagerDto(Manager manager);
}
