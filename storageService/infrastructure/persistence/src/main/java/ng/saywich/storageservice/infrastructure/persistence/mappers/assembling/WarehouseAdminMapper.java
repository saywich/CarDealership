package ng.saywich.storageservice.infrastructure.persistence.mappers.assembling;

import ng.saywich.storageservice.domain.assembling.WarehouseAdmin;
import ng.saywich.storageservice.infrastructure.persistence.models.assembling.WarehouseAdminDao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WarehouseAdminMapper {
    WarehouseAdminDao toWarehouseAdminDao(WarehouseAdmin admin);

    WarehouseAdmin toWarehouseAdmin(WarehouseAdminDao dao);
}