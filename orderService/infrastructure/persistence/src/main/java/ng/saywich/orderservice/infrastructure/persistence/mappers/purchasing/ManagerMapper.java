package ng.saywich.orderservice.infrastructure.persistence.mappers.purchasing;

import ng.saywich.shared.domain.purchasing.Manager;
import ng.saywich.orderservice.infrastructure.persistence.models.purchasing.ManagerDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ManagerMapper {
    @Mapping(target = "id", source = "manager.managerId")
    public ManagerDao toManagerDao(Manager manager);

    @Mapping(target = "managerId", source = "dao.id")
    public Manager toManager(ManagerDao dao);
}
